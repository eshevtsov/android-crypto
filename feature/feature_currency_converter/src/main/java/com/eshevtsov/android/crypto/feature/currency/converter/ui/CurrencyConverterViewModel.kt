package com.eshevtsov.android.crypto.feature.currency.converter.ui

import androidx.lifecycle.*
import com.eshevtsov.android.crypto.core.feature.Scheduler
import com.eshevtsov.android.crypto.core.feature.format
import com.eshevtsov.android.crypto.data.dto.CoinIdDto
import com.eshevtsov.android.crypto.feature.currency.converter.domain.ConvertCurrencyUseCase
import com.eshevtsov.android.crypto.feature.currency.converter.domain.FormatConversionUseCase
import com.eshevtsov.android.crypto.feature.currency.converter.domain.GetCoinIdsListUseCase
import kotlinx.coroutines.launch

class CurrencyConverterViewModel(
    private val getCoinIds: GetCoinIdsListUseCase,
    private val convertCurrency: ConvertCurrencyUseCase,
    private val formatConversion: FormatConversionUseCase,
    private val scheduler: Scheduler
) : ViewModel() {
    val progress = MutableLiveData<Boolean>()

    val coinIds: LiveData<List<CoinIdDto>> =
        liveData(scheduler.IO) {
            progress.postValue(true)
            val items = getCoinIds()
            emit(items)
            fromCoin.postValue(items[0].symbol)
            toCoin.postValue(items[0].symbol)
            progress.postValue(false)
        }

    val fromAmount = MutableLiveData<String>()
    val toAmount = MutableLiveData<String>()

    val fromCoin = MutableLiveData<String>()
    val toCoin = MutableLiveData<String>()

    val conversionResult: LiveData<String> =
        MediatorLiveData<String>().apply {
            val observer = Observer<String> {
                viewModelScope.launch(scheduler.IO) {
                    progress.postValue(true)
                    this@apply.postValue(convertCurrency())
                    progress.postValue(false)
                }
            }
            this.addSource(fromAmount, observer)
            this.addSource(fromCoin, observer)
            this.addSource(toCoin, observer)
        }

    private suspend fun convertCurrency(): String? {
        val from = findCoinBySymbol(fromCoin.value) ?: return null
        val to = findCoinBySymbol(toCoin.value) ?: return null

        val doubleAmount = runCatching { fromAmount.value!!.toDouble() }
            .getOrDefault(0.0)

        val result = convertCurrency(doubleAmount, from, to)
        toAmount.postValue(result.format())

        return formatConversion(doubleAmount, from, result, to)
    }

    private fun findCoinBySymbol(symbol: String?) =
        coinIds.value?.firstOrNull { it.symbol == symbol }

    fun swapCoins() {
        val temp = fromCoin.value
        fromCoin.value = toCoin.value
        toCoin.postValue(temp)
    }
}
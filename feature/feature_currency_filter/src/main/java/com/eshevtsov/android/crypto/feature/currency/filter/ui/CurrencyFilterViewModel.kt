package com.eshevtsov.android.crypto.feature.currency.filter.ui

import androidx.lifecycle.*
import com.eshevtsov.android.crypto.core.feature.Scheduler
import com.eshevtsov.android.crypto.core.feature.SingleLiveEvent
import com.eshevtsov.android.crypto.data.dto.CoinIdDto
import com.eshevtsov.android.crypto.feature.currency.filter.domain.GetCoinIdsUseCase
import com.eshevtsov.android.crypto.feature.currency.filter.domain.SaveFilterUseCase
import kotlinx.coroutines.launch

class CurrencyFilterViewModel(
    private val getCoinIds: GetCoinIdsUseCase,
    private val saveFilter: SaveFilterUseCase,
    private val scheduler: Scheduler
) : ViewModel() {
    val coinIds: LiveData<List<CoinIdDto>> =
        liveData(scheduler.IO) {
            progress.postValue(true)
            emit(getCoinIds())
            progress.postValue(false)
        }

    val saved = SingleLiveEvent<Boolean>()
    val progress = MutableLiveData<Boolean>()

    fun saveFilters() {
        viewModelScope.launch(scheduler.IO) {
            coinIds.value?.let { saveFilter(it) }
            saved.postValue(true)
        }
    }
}
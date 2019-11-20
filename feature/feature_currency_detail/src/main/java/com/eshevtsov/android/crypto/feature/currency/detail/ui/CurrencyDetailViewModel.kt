package com.eshevtsov.android.crypto.feature.currency.detail.ui

import androidx.lifecycle.*
import com.eshevtsov.android.crypto.core.feature.Scheduler
import com.eshevtsov.android.crypto.data.dto.CoinDetailDto
import com.eshevtsov.android.crypto.feature.currency.detail.domain.GetCoinDetailModelUseCase
import kotlinx.coroutines.launch

class CurrencyDetailViewModel(
    private val getCoinDetailModel: GetCoinDetailModelUseCase,
    private val scheduler: Scheduler
) : ViewModel() {
    val coinDetail = MutableLiveData<CoinDetailDto>()

    fun initModel(id: Int) {
        viewModelScope.launch(scheduler.IO) {
            coinDetail.postValue(getCoinDetailModel(id))
        }
    }
}
package com.eshevtsov.android.crypto.feature.currency.list.ui

import androidx.lifecycle.*
import com.eshevtsov.android.crypto.core.feature.Scheduler
import com.eshevtsov.android.crypto.feature.currency.list.domain.CoinModel
import com.eshevtsov.android.crypto.feature.currency.list.domain.GetCoinModelsUseCase
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val getCoinModels: GetCoinModelsUseCase,
    private val scheduler: Scheduler
) : ViewModel() {
    val coinModels = MutableLiveData<List<CoinModel>>()
    val progress = MutableLiveData<Boolean>()

    fun initData() {
        viewModelScope.launch(scheduler.IO) {
            progress.postValue(true)
            coinModels.postValue(getCoinModels())
            progress.postValue(false)
        }
    }
}
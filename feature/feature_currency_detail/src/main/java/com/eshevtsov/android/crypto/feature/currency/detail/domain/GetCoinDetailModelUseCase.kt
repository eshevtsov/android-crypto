package com.eshevtsov.android.crypto.feature.currency.detail.domain

import com.eshevtsov.android.crypto.data.ICoinDetailsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinDetailDto

class GetCoinDetailModelUseCase(
    private val dataSource: ICoinDetailsDataSource
) {
    suspend operator fun invoke(id: Int): CoinDetailDto = dataSource.detailInfo(id)
}
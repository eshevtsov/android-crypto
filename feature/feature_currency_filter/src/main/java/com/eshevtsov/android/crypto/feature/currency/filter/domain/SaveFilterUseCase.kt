package com.eshevtsov.android.crypto.feature.currency.filter.domain

import com.eshevtsov.android.crypto.data.ICoinIdsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

class SaveFilterUseCase(
    private val dataSource: ICoinIdsDataSource
) {
    suspend operator fun invoke(coinIds: List<CoinIdDto>) = dataSource.update(coinIds)
}
package com.eshevtsov.android.crypto.feature.currency.filter.domain

import com.eshevtsov.android.crypto.data.ICoinIdsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

class GetCoinIdsUseCase(
    private val dataSource: ICoinIdsDataSource
) {
    suspend operator fun invoke(): List<CoinIdDto> =
        dataSource.list()
            .sortedBy { it.symbol }
}
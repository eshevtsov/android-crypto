package com.eshevtsov.android.crypto.feature.currency.converter.domain

import com.eshevtsov.android.crypto.data.ICoinIdsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinIdDto
import com.eshevtsov.android.crypto.data.dto.UsdCoinIdDto

class GetCoinIdsListUseCase(
    private val dataSource: ICoinIdsDataSource
) {
    suspend operator fun invoke(): List<CoinIdDto> =
        dataSource.list()
            .sortedBy { it.symbol }
            .toMutableList()
            .apply {
                add(0, UsdCoinIdDto)
            }
}
package com.eshevtsov.android.crypto.feature.currency.list.domain

import com.eshevtsov.android.crypto.data.ICoinListDataSource

class GetCoinModelsUseCase(
    private val dataSource: ICoinListDataSource,
    private val mapCoinDto: MapCoinDtoUseCase
) {
    suspend operator fun invoke(): List<CoinModel> =
        dataSource.list()
            .filter { it.visible }
            .map { mapCoinDto(it) }
            .sortedBy { it.symbol }
}
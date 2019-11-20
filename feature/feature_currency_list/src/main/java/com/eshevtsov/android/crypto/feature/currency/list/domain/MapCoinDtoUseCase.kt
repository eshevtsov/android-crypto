package com.eshevtsov.android.crypto.feature.currency.list.domain

import com.eshevtsov.android.crypto.data.dto.CoinDto

class MapCoinDtoUseCase(
    private val priceSymbol: String = "USD",
    private val defaultPrice: String = "-"
) {
    operator fun invoke(coinDto: CoinDto): CoinModel {
        val price = coinDto.quote[priceSymbol]?.price ?: defaultPrice
        val priceStr = "$price $priceSymbol"

        return CoinModel(
            id = coinDto.id,
            name = coinDto.name,
            symbol = coinDto.symbol,
            price = priceStr
        )
    }
}
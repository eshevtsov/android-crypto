package com.eshevtsov.android.crypto.data.dto

data class CoinIdDto(
    val id: Int,
    val symbol: String,
    var visible: Boolean = true
)

val UsdCoinIdDto = CoinIdDto(2781, "USD")
package com.eshevtsov.android.crypto.feature.currency.list.domain

data class CoinModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: String
)
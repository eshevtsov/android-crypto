package com.eshevtsov.android.crypto.data

import com.eshevtsov.android.crypto.data.dto.CoinDto

interface ICryptoToolsDataSource {
    suspend fun convert(amount: Double, fromId: Int, toId: Int): CoinDto
}
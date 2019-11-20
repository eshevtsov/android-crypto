package com.eshevtsov.android.crypto.data

import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto

interface ICryptoToolsDataSource {
    suspend fun conversionRequest(
        amount: Double,
        fromId: Int,
        toId: Int
    ): CoinListResponseDto
}
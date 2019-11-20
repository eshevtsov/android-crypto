package com.eshevtsov.android.crypto.data.repository

import com.eshevtsov.android.crypto.data.ICryptoToolsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto
import com.eshevtsov.android.crypto.data.network.CryptoToolsService

class CryptoToolsRepository(
    private val remoteDataSource: CryptoToolsService
) : ICryptoToolsDataSource {

    override suspend fun conversionRequest(
        amount: Double,
        fromId: Int,
        toId: Int
    ): CoinListResponseDto =
        remoteDataSource.conversionRequest(amount, fromId, toId)
}
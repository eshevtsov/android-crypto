package com.eshevtsov.android.crypto.data.repository

import com.eshevtsov.android.crypto.data.ICryptoToolsDataSource
import com.eshevtsov.android.crypto.data.ONE_MINUTE_IN_MILLIS
import com.eshevtsov.android.crypto.data.currentMillis
import com.eshevtsov.android.crypto.data.database.CryptoRoomDatabase
import com.eshevtsov.android.crypto.data.dto.CoinDto
import com.eshevtsov.android.crypto.data.network.CryptoToolsService

class CryptoToolsRepository(
    private val remote: CryptoToolsService
) : ICryptoToolsDataSource {

    override suspend fun convert(
        amount: Double,
        fromId: Int,
        toId: Int
    ): CoinDto =
        remote.convert(amount, fromId, toId).data
}
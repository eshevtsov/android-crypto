package com.eshevtsov.android.crypto.data.repository

import com.eshevtsov.android.crypto.data.ICoinListDataSource
import com.eshevtsov.android.crypto.data.ONE_MINUTE_IN_MILLIS
import com.eshevtsov.android.crypto.data.currentMillis
import com.eshevtsov.android.crypto.data.database.CryptoRoomDatabase
import com.eshevtsov.android.crypto.data.dto.CoinDto
import com.eshevtsov.android.crypto.data.network.CoinListService

class CoinListRepository(
    private val remote: CoinListService,
    private val database: CryptoRoomDatabase,
    private val updateInterval: Long = 2 * ONE_MINUTE_IN_MILLIS
) : ICoinListDataSource {
    private var lastListUpdate: Long = currentMillis() + updateInterval

    override suspend fun list(): List<CoinDto> {
        var cash = database.coinDao().list()
        if (shouldUpdate(lastListUpdate) || cash.isEmpty()) {
            val resultDto = remote.list()
            cash = resultDto.data

            database.coinDao().insertOrUpdateAll(*cash.toTypedArray())
            lastListUpdate = currentMillis()
        }
        return cash
    }

    private fun shouldUpdate(lastUpdate: Long) = currentMillis() - lastUpdate >= updateInterval
}


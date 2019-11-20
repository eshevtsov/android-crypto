package com.eshevtsov.android.crypto.data.repository

import com.eshevtsov.android.crypto.data.ICoinDetailsDataSource
import com.eshevtsov.android.crypto.data.ONE_MINUTE_IN_MILLIS
import com.eshevtsov.android.crypto.data.currentMillis
import com.eshevtsov.android.crypto.data.database.CryptoRoomDatabase
import com.eshevtsov.android.crypto.data.dto.CoinDetailDto
import com.eshevtsov.android.crypto.data.network.CoinDetailsService

class CoinDetailsRepository(
    private val remote: CoinDetailsService,
    private val database: CryptoRoomDatabase,
    private val updateInterval: Long = 2 * ONE_MINUTE_IN_MILLIS
) : ICoinDetailsDataSource {
    private var lastDetailUpdate: Long = currentMillis() + updateInterval

    override suspend fun detailInfo(id: Int): CoinDetailDto {
        var cash = database.coinDetailDao()
            .listById(intArrayOf(id))
            .firstOrNull { it.id == id }

        if (shouldUpdate(lastDetailUpdate) || cash == null) {
            val stringId = id.toString()
            val resultDto = remote.detailInfo(stringId)
            cash = resultDto.data[stringId]
                ?: error("Can't get coin detail info for #id $id.")

            database.coinDetailDao().insertAll(cash)
            lastDetailUpdate = currentMillis()
        }

        return cash
    }

    private fun shouldUpdate(lastUpdate: Long) = currentMillis() - lastUpdate >= updateInterval
}


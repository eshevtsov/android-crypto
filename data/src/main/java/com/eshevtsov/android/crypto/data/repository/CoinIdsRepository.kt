package com.eshevtsov.android.crypto.data.repository

import com.eshevtsov.android.crypto.data.ICoinIdsDataSource
import com.eshevtsov.android.crypto.data.ICoinListDataSource
import com.eshevtsov.android.crypto.data.database.CryptoRoomDatabase
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

class CoinIdsRepository(
    private val listDataSource: ICoinListDataSource,
    private val database: CryptoRoomDatabase
) : ICoinIdsDataSource {

    override suspend fun list(): List<CoinIdDto> {
        var cash = database.coinDao().listIds()
        if (cash.isEmpty()) {
            cash = listDataSource.list().map { CoinIdDto(it.id, it.symbol) }
        }
        return cash
    }

    override suspend fun update(values: List<CoinIdDto>) {
        database.coinDao().updateAll(*values.toTypedArray())
    }
}


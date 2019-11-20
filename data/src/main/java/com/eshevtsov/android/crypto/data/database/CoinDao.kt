package com.eshevtsov.android.crypto.data.database

import androidx.room.*
import com.eshevtsov.android.crypto.data.dto.CoinDto
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin")
    suspend fun list(): List<CoinDto>

    @Query("SELECT id, symbol, visible FROM coin")
    suspend fun listIds(): List<CoinIdDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg coins: CoinDto): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(vararg coins: CoinDto): List<Long>

    @Transaction
    suspend fun insertOrUpdateAll(vararg coins: CoinDto) {
        val rowIds = insertAll(*coins)
        val shouldUpdated = rowIds.mapIndexedNotNull { index, rowID ->
            if (rowID == -1L) null else coins[index]
        }
        updateAll(*shouldUpdated.toTypedArray())
    }

    @Query("UPDATE coin SET visible = :visible WHERE id = :id")
    suspend fun update(id: Int, visible: Boolean)

    @Transaction
    suspend fun updateAll(vararg coinsIds: CoinIdDto) {
        for (coinId in coinsIds) {
            update(coinId.id, coinId.visible)
        }
    }
}
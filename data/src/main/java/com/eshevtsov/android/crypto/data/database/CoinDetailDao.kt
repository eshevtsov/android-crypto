package com.eshevtsov.android.crypto.data.database

import androidx.room.*
import com.eshevtsov.android.crypto.data.dto.CoinDetailDto

@Dao
interface CoinDetailDao {
    @Query("SELECT * FROM coinDetail WHERE id IN (:ids)")
    suspend fun listById(ids: IntArray): List<CoinDetailDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg coins: CoinDetailDto)
}
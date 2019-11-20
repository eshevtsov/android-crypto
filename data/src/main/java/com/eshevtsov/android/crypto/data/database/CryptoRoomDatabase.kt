package com.eshevtsov.android.crypto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eshevtsov.android.crypto.data.dto.CoinDetailDto
import com.eshevtsov.android.crypto.data.dto.CoinDto

@Database(
    entities = [CoinDto::class, CoinDetailDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    TypeAdapters::class
)
abstract class CryptoRoomDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
    abstract fun coinDetailDao(): CoinDetailDao
}
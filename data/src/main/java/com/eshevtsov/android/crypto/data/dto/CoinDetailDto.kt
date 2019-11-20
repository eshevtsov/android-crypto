package com.eshevtsov.android.crypto.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "coinDetail")
data class CoinDetailDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val symbol: String,
    val category: String,

    @Json(name = "date_added")
    val dateAdded:  String? = null,

    val status: String? = null,
    val slug: String,
    val logo: String? = null,
    val description: String? = null,
    val notice: String? = null,
    val tags: List<String>? = null,
    val urls: Map<String, List<String>>? = null
)
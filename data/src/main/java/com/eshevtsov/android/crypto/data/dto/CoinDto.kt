package com.eshevtsov.android.crypto.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "coin")
data class CoinDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val quote: Map<String, QuoteObjectDto>,

    @Json(name = "cmc_rank")
    val cmcRank: Int,

    @Json(name = "num_market_pairs")
    val numMarketPairs: Int,

    @Json(name = "circulating_supply")
    val circulatingSupply: Double? = null,

    @Json(name = "total_supply")
    val totalSupply: Double? = null,

    @Json(name = "market_cap_by_total_supply")
    val marketCapByTotalSupply: Double? = null,

    @Json(name = "max_supply")
    val maxSupply: Double? = null,

    @Json(name = "last_updated")
    val lastUpdated: String? = null,

    @Json(name = "date_added")
    val dateAdded:  String? = null,

    var visible: Boolean = true
)
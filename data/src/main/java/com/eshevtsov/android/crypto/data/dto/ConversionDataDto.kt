package com.eshevtsov.android.crypto.data.dto

import com.squareup.moshi.Json

data class ConversionDataDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val amount: Double,
    val quote: Map<String, QuoteObjectDto>,

    @Json(name = "last_updated")
    val lastUpdated:  String
)
package com.eshevtsov.android.crypto.data.dto

import com.squareup.moshi.Json

data class QuoteObjectDto(
    val price: Double,

    @Json(name = "last_updated")
    val lastUpdated:  String
)
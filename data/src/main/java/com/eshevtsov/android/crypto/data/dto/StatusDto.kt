package com.eshevtsov.android.crypto.data.dto

import com.squareup.moshi.Json

data class StatusDto(
    val timestamp: String,
    val elapsed: Int,

    @Json(name = "credit_count")
    val creditCount: String,

    @Json(name = "error_code")
    val errorCode: Int? = null,

    @Json(name = "error_message")
    val errorMessage: String? = null
)
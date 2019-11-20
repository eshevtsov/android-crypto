package com.eshevtsov.android.crypto.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class BooleanValue

class BooleanAdapter {
    @ToJson
    fun toJson(@BooleanValue value: Boolean): Int = if (value) 1 else 0

    @FromJson
    @BooleanValue
    fun fromJson(value: Int): Boolean = value == 1
}
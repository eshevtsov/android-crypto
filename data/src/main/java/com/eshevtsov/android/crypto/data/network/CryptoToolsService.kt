package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinsConvertResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoToolsService {

    @GET("tools/price-conversion")
    suspend fun convert(
        @Query("amount") amount: Double,
        @Query("id") fromId: Int,
        @Query("convert_id") toId: Int
    ): CoinsConvertResponseDto
}
package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoToolsService {

    @GET("tools/price-conversion")
    suspend fun conversionRequest(
        @Query("amount") amount: Double,
        @Query("id") fromId: Int,
        @Query("convert") toId: Int
    ): CoinListResponseDto
}
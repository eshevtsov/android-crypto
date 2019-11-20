package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinsInfoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinDetailsService {

    @GET("info")
    suspend fun detailInfo(@Query("id") coinsIds: String): CoinsInfoResponseDto
}
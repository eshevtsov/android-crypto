package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinListService {

    @GET("cryptocurrency/listings/latest")
    suspend fun list(@Query("limit") limit: Int = 1000): CoinListResponseDto
}
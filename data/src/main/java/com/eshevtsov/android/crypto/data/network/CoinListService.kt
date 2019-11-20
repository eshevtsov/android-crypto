package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto
import retrofit2.http.GET

interface CoinListService {

    @GET("cryptocurrency/listings/latest?limit=5000")
    suspend fun list(): CoinListResponseDto
}
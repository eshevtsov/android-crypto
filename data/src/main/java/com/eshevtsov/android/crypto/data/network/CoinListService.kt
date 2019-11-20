package com.eshevtsov.android.crypto.data.network

import com.eshevtsov.android.crypto.data.dto.CoinListResponseDto
import retrofit2.http.GET

interface CoinListService {

    @GET("listings/latest")
    suspend fun list(): CoinListResponseDto
}
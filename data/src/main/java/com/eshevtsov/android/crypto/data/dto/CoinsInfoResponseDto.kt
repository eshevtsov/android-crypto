package com.eshevtsov.android.crypto.data.dto

data class CoinsInfoResponseDto(
    val data: Map<String, CoinDetailDto>,
    val status: StatusDto
)
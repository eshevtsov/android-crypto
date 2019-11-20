package com.eshevtsov.android.crypto.data.dto

data class CoinListResponseDto(
    val data: List<CoinDto>,
    val status: StatusDto
)
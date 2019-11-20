package com.eshevtsov.android.crypto.data

import com.eshevtsov.android.crypto.data.dto.CoinDto

interface ICoinListDataSource {
    suspend fun list(): List<CoinDto>
}
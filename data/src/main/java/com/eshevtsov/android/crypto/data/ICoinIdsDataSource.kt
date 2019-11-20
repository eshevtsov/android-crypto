package com.eshevtsov.android.crypto.data

import com.eshevtsov.android.crypto.data.dto.CoinIdDto

interface ICoinIdsDataSource {
    suspend fun list(): List<CoinIdDto>
    suspend fun update(values: List<CoinIdDto>)
}
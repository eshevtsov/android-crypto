package com.eshevtsov.android.crypto.data

import com.eshevtsov.android.crypto.data.dto.CoinDetailDto

interface ICoinDetailsDataSource {
    suspend fun detailInfo(id: Int): CoinDetailDto?
}
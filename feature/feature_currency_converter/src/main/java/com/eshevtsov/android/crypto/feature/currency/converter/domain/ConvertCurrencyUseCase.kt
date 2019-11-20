package com.eshevtsov.android.crypto.feature.currency.converter.domain

import com.eshevtsov.android.crypto.data.ICryptoToolsDataSource
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

class ConvertCurrencyUseCase(
    private val cryptoTools: ICryptoToolsDataSource
) {
    suspend operator fun invoke(
        amount: Double,
        from: CoinIdDto,
        to: CoinIdDto
    ): Double {
        if (amount < 1e-8 || amount > 1000000000) {
            return 0.0
        }
        return runCatching {
            val resultDto = cryptoTools.convert(amount, from.id, to.id)
            resultDto.quote.getValue(to.id.toString()).price
        }.getOrElse { 0.0 }
    }
}
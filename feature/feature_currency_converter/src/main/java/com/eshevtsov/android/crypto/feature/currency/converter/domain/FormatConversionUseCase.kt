package com.eshevtsov.android.crypto.feature.currency.converter.domain

import com.eshevtsov.android.crypto.core.feature.format
import com.eshevtsov.android.crypto.data.dto.CoinIdDto

class FormatConversionUseCase {
    operator fun invoke(
        fromAmount: Double,
        from: CoinIdDto,
        toAmount: Double,
        to: CoinIdDto
    ): String? =
        "${fromAmount.format()} ${from.symbol} = ${toAmount.format()} ${to.symbol}"
}
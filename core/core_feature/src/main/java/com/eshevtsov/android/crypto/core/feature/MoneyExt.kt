package com.eshevtsov.android.crypto.core.feature

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double?.format(pattern: String = "#0.00"): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
        .apply { decimalSeparator = '.' }
    return DecimalFormat(pattern, symbols).format(this)
}
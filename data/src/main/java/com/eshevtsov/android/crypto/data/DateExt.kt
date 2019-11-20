package com.eshevtsov.android.crypto.data

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringISO8601UTC(): String = dateFormatISO8601UTC().format(this)
fun String.toDateISO8601UTC(): Date? = dateFormatISO8601UTC().parse(this)

private fun dateFormatISO8601UTC() =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ROOT)
        .apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

fun currentMillis() = Calendar.getInstance().timeInMillis

const val ONE_SECOND_IN_MILLIS = 1000L
const val ONE_MINUTE_IN_MILLIS = 60L * ONE_SECOND_IN_MILLIS

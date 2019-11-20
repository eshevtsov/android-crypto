package com.eshevtsov.android.crypto.data.database

import androidx.room.TypeConverter
import com.eshevtsov.android.crypto.data.dto.QuoteObjectDto

object TypeAdapters {

    @TypeConverter
    @JvmStatic
    fun quoteToString(quote: Map<String, QuoteObjectDto>?): String? = quote
        ?.map {
            "${it.key}:${it.value.price}:${it.value.lastUpdated}"
        }
        ?.joinToString(";")


    @TypeConverter
    @JvmStatic
    fun quoteFromString(str: String?): Map<String, QuoteObjectDto>? =
        runCatching {
            str ?: return@runCatching null

            val items = str.split(";")
            val map = mutableMapOf<String, QuoteObjectDto>()
            for (item in items) {
                val keys = item.split(":")
                val id = keys[0]
                val quote = QuoteObjectDto(keys[1].toDouble(), keys[2])
                map[id] = quote
            }
            return@runCatching map
        }.getOrNull()

    @TypeConverter
    @JvmStatic
    fun listToString(list: List<String>?): String? = list?.joinToString(";")

    @TypeConverter
    @JvmStatic
    fun listFromString(str: String?): List<String>? = str?.split(";")

    @TypeConverter
    @JvmStatic
    fun urlsToString(urls: Map<String, List<String>>?): String? = urls
        ?.filterValues { it.isNotEmpty() }
        ?.map {
            "${it.key}\t${listToString(it.value)}"
        }
        ?.joinToString("\n")


    @TypeConverter
    @JvmStatic
    fun urlsFromString(str: String?): Map<String, List<String>>? =
        runCatching {
            str ?: return@runCatching null

            val items = str.split("\n")
            val map = mutableMapOf<String, List<String>>()
            for (item in items) {
                val keys = item.split("\t")
                map[keys[0]] = listFromString(keys[1])!!
            }
            return@runCatching map
        }.getOrNull()
}
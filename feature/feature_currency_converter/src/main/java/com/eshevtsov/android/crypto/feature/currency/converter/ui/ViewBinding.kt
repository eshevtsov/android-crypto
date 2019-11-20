package com.eshevtsov.android.crypto.feature.currency.converter.ui

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.eshevtsov.android.crypto.data.dto.CoinIdDto
import com.eshevtsov.android.crypto.feature.currency.converter.R

object ViewBinding {

    @JvmStatic
    @BindingAdapter("coins")
    fun AutoCompleteTextView.setCoins(
        items: List<CoinIdDto>?
    ) {
        items ?: return

        val symbols = items.map { it.symbol }.toTypedArray()
        val arrayAdapter = ArrayAdapter(
            context,
            R.layout.layout_convert_dropdown_menu_item,
            symbols
        )
        this.setAdapter(arrayAdapter)
    }
}
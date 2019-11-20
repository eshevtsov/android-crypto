package com.eshevtsov.android.crypto.feature.currency.converter.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.eshevtsov.android.crypto.feature.currency.converter.R
import com.eshevtsov.android.crypto.feature.currency.converter.databinding.FragmentCurrencyConverterBinding

class CurrencyConverterFragment(
    private val viewModel: CurrencyConverterViewModel
) : Fragment(R.layout.fragment_currency_converter) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentCurrencyConverterBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CurrencyConverterFragment.viewModel
        }
    }
}
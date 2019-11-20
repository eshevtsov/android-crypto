package com.eshevtsov.android.crypto.feature.currency.detail.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eshevtsov.android.crypto.feature.currency.detail.R
import com.eshevtsov.android.crypto.feature.currency.detail.databinding.FragmentCurrencyDetailBinding

class CurrencyDetailFragment(
    private val viewModel: CurrencyDetailViewModel
) : Fragment(R.layout.fragment_currency_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val id = arguments?.getInt("id")
            id?.let { viewModel.initModel(it) }
        }

        FragmentCurrencyDetailBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CurrencyDetailFragment.viewModel
        }
    }
}
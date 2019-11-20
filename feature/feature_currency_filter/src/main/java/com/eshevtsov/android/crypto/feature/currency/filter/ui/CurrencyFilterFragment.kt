package com.eshevtsov.android.crypto.feature.currency.filter.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.eshevtsov.android.crypto.feature.currency.filter.R
import com.eshevtsov.android.crypto.feature.currency.filter.databinding.FragmentCurrencyFilterBinding

class CurrencyFilterFragment(
    private val viewModel: CurrencyFilterViewModel,
    private val navigation: CurrencyFilterNavigation
) : Fragment(R.layout.fragment_currency_filter) {

    private val currencyAdapter = CurrencyFilterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.coinIds.observe(viewLifecycleOwner, Observer { items ->
            currencyAdapter.setItems(items)
        })
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            navigation.navigateToList()
        })

        FragmentCurrencyFilterBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = currencyAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_currency_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_currency_filter_option_save -> {
                viewModel.saveFilters()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
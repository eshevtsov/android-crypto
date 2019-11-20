package com.eshevtsov.android.crypto.feature.currency.list.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.eshevtsov.android.crypto.feature.currency.list.R
import com.eshevtsov.android.crypto.feature.currency.list.databinding.FragmentCurrencyListBinding

class CurrencyListFragment(
    private val viewModel: CurrencyListViewModel,
    private val navigation: CurrencyListNavigation
) : Fragment(R.layout.fragment_currency_list) {

    private lateinit var currencyAdapter: CurrencyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyAdapter = CurrencyListAdapter { model ->
            navigation.navigateToDetail(model.id)
        }

        viewModel.coinModels.observe(viewLifecycleOwner, Observer { items ->
            currencyAdapter.setItems(items)
        })
        viewModel.initData()

        FragmentCurrencyListBinding.bind(view).apply {
            adapter = currencyAdapter
        }
    }

    override fun onDestroyView() {
        currencyAdapter.release()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_currency_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_currency_list_option_filter -> {
                navigation.navigateToFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
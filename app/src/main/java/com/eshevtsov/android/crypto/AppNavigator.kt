package com.eshevtsov.android.crypto

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.eshevtsov.android.crypto.feature.currency.filter.ui.CurrencyFilterNavigation
import com.eshevtsov.android.crypto.feature.currency.list.ui.CurrencyListNavigation

class AppNavigator(
    private var navController: NavController? = null
) : CurrencyListNavigation,
    CurrencyFilterNavigation {

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }

    fun navigate(actionId: Int) {
        navController?.navigate(actionId)
    }

    override fun navigateToDetail(id: Int) {
        navController?.navigate(
            R.id.action_currency_list_to_detail,
            bundleOf("id" to id)
        )
    }

    override fun navigateToFilter() =
        navigate(R.id.action_currency_list_to_filter)

    override fun navigateToList() {
        navController?.popBackStack()
    }
}

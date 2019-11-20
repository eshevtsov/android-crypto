package com.eshevtsov.android.crypto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.eshevtsov.android.crypto.feature.currency.detail.ui.CurrencyDetailFragment
import com.eshevtsov.android.crypto.feature.currency.filter.ui.CurrencyFilterFragment
import com.eshevtsov.android.crypto.feature.currency.list.ui.CurrencyListFragment
import org.koin.core.Koin

class AppFragmentFactory(
    private val koin: Koin
) : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            CurrencyListFragment::class.java -> CurrencyListFragment(koin.get(), koin.get())
            CurrencyDetailFragment::class.java -> CurrencyDetailFragment(koin.get())
            CurrencyFilterFragment::class.java -> CurrencyFilterFragment(koin.get(), koin.get())
            else -> super.instantiate(classLoader, className)
        }
}
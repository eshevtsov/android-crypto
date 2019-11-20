package com.eshevtsov.android.crypto

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.eshevtsov.android.crypto.core.feature.bindView
import com.eshevtsov.android.crypto.core.feature.createConnectionLostSnackbar
import com.eshevtsov.android.crypto.data.network.ConnectionReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class LauncherActivity : AppCompatActivity(R.layout.activity_main) {

    private val bottomNav: BottomNavigationView by bindView(R.id.bottom_navigation)
    private val navigator: AppNavigator by inject()

    private val navHostFragment: NavHostFragment?
        get() = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as? NavHostFragment

    private val navController: NavController?
        get() = navHostFragment?.navController

    private val connectionReceiver by lazy { ConnectionReceiver(this) }
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = get()
        super.onCreate(savedInstanceState)

        navController?.let {
            navigator.bind(it)
            NavigationUI.setupWithNavController(bottomNav, it)
        }
        navHostFragment?.childFragmentManager?.run {
            addOnBackStackChangedListener {
                supportActionBar?.setDisplayHomeAsUpEnabled(backStackEntryCount > 0)
                closeKeyboard()
            }
        }
        connectionReceiver.registerNetworkCallback(
            onLost = {
                if (snackbar == null) {
                    snackbar = createConnectionLostSnackbar().also { it.show() }
                }
            },
            onConnect = {
                snackbar?.dismiss()
                snackbar = null
            }
        )
    }

    private fun closeKeyboard() {
        window.currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    override fun onSupportNavigateUp() = navController?.navigateUp() ?: false

    override fun onDestroy() {
        connectionReceiver.unregisterNetworkCallback()
        navigator.unbind()
        super.onDestroy()
    }
}
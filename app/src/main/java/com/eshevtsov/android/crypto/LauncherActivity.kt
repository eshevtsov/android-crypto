package com.eshevtsov.android.crypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class LauncherActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: AppNavigator by inject()

    private val navHostFragment: NavHostFragment?
        get() = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as? NavHostFragment

    private val navController: NavController?
        get() = navHostFragment?.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = get()
        super.onCreate(savedInstanceState)

        navController?.let { navigator.bind(it) }
        navHostFragment?.childFragmentManager?.run {
            addOnBackStackChangedListener {
                supportActionBar?.setDisplayHomeAsUpEnabled(backStackEntryCount > 0)
            }
        }
    }

    override fun onSupportNavigateUp() = navController?.navigateUp() ?: false

    override fun onDestroy() {
        navigator.unbind()
        super.onDestroy()
    }
}
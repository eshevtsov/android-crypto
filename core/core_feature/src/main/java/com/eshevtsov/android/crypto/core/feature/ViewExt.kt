package com.eshevtsov.android.crypto.core.feature

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

inline fun <reified T : View> Activity.bindView(@IdRes id: Int): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { this.findViewById(id) as T }
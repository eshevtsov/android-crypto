package com.eshevtsov.android.crypto.core.feature

import android.app.Activity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun Activity.createConnectionLostSnackbar(): Snackbar {
    val view = window?.decorView?.findViewById<View>(android.R.id.content)
    val message = getString(R.string.lose_internet_connection)
    return Snackbar.make(view!!, message, Snackbar.LENGTH_INDEFINITE).apply {
        this.view.setBackgroundResource(R.color.color_warning)
        this.view.layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            val sideMargin = resources.getDimensionPixelSize(R.dimen.spacing_16)
            val topMargin = (resources.displayMetrics.heightPixels * 0.73).toInt()
            this.setMargins(sideMargin, topMargin, sideMargin, 0)
        }
        val mainTextView = getView().findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        mainTextView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimensionPixelSize(R.dimen.textSize_16).toFloat()
        )
    }
}
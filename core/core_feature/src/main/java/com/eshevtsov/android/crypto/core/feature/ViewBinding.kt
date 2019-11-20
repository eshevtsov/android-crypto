package com.eshevtsov.android.crypto.core.feature

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ViewBinding {

    @JvmStatic
    @BindingAdapter("srcUrl")
    fun ImageView.setSrcUrl(srcUrl: String?) {
        srcUrl?.let {
            Picasso.get().load(it).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun View.setVisibility(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
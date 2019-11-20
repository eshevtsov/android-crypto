package com.eshevtsov.android.crypto.feature.currency.detail.ui

import android.widget.ExpandableListView
import androidx.databinding.BindingAdapter
import com.eshevtsov.android.crypto.core.feature.HeaderBodyExpandableListAdapter

object ViewBinding {

    @JvmStatic
    @BindingAdapter("items")
    fun ExpandableListView.setItems(
        items: Map<String, List<String>>?
    ) {
        items ?: return

        val filtered = items.filterValues { it.isNotEmpty() }
        setAdapter(HeaderBodyExpandableListAdapter(context, filtered))
    }
}
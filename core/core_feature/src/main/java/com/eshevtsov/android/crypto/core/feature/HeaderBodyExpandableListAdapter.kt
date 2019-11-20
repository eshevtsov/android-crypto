package com.eshevtsov.android.crypto.core.feature

import android.content.Context
import android.widget.SimpleExpandableListAdapter
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class HeaderBodyExpandableListAdapter constructor(
    context: Context,
    items: Map<String, List<String>>,
    @LayoutRes headerLayout: Int = R.layout.simple_list_header_layout,
    @IdRes headerTVIdRes: Int = android.R.id.text1,
    @LayoutRes bodyLayout: Int = R.layout.simple_list_body_layout,
    @IdRes bodyTVIdRes: Int = android.R.id.text1
) : SimpleExpandableListAdapter(
    context,
    createHeaderData(items.keys), headerLayout, arrayOf(HEADER_ATTR), intArrayOf(headerTVIdRes),
    createBodyData(items.values), bodyLayout, arrayOf(BODY_ATTR), intArrayOf(bodyTVIdRes)
) {
    companion object {
        private const val HEADER_ATTR = "headerName"
        private const val BODY_ATTR = "bodyName"

        private fun createHeaderData(items: Collection<String>) =
            ArrayList<Map<String, String>>().apply {
                for (header in items) {
                    add(mapOf(HEADER_ATTR to header))
                }
            }

        private fun createBodyData(items: Collection<Collection<String>>) =
            ArrayList<ArrayList<Map<String, String>>>().apply {
                for (bodyItems in items) {
                    val bodyList = arrayListOf<Map<String, String>>()
                    for (body in bodyItems) {
                        bodyList.add(mapOf(BODY_ATTR to body))
                    }
                    add(bodyList)
                }
            }
    }
}
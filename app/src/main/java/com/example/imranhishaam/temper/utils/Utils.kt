package com.example.imranhishaam.temper.utils

/**
 * Created by imranhishaam on 3/12/18.
 */

import android.content.Context
import android.content.res.TypedArray

import com.example.imranhishaam.temper.R

object Utils {

    fun getToolbarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(
                intArrayOf(R.attr.actionBarSize))
        val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        return toolbarHeight
    }

    fun getTabsHeight(context: Context): Int {
        return context.resources.getDimension(R.dimen.tabsHeight).toInt()
    }

}

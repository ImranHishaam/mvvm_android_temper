package com.example.imranhishaam.temper.utils

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View

class ScrollingFABBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {
    private val toolbarHeight: Int

    init {
        this.toolbarHeight = Utils.getToolbarHeight(context)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, fab: FloatingActionButton?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, fab: FloatingActionButton?, dependency: View?): Boolean {
        if (dependency is AppBarLayout) {
            val lp = fab!!.layoutParams as CoordinatorLayout.LayoutParams
            val fabBottomMargin = lp.bottomMargin
            val distanceToScroll = fab.height + fabBottomMargin
            val ratio = dependency.y / toolbarHeight.toFloat()
            fab.translationY = -distanceToScroll * ratio
        }
        return true
    }
}
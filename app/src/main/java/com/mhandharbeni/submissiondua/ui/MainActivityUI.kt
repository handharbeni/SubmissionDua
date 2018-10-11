package com.mhandharbeni.submissiondua.ui

import android.support.design.widget.TabLayout.GRAVITY_FILL
import android.support.design.widget.TabLayout.MODE_FIXED
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class MainActivityUI<T>: AnkoComponent<T>{

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout{
            orientation = LinearLayout.VERTICAL
            tabLayout {
                id = R.id.tabMain
                tabMode = MODE_FIXED
                tabGravity = GRAVITY_FILL
            }.lparams(width = matchParent, height = wrapContent)
            viewPager {
                id = R.id.viewPagerMain
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
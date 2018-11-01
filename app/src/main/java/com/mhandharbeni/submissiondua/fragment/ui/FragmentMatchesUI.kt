package com.mhandharbeni.submissiondua.fragment.ui

import android.support.design.widget.TabLayout
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class FragmentMatchesUI<T> : AnkoComponent<T> {

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout{
            orientation = LinearLayout.VERTICAL
            tabLayout {
                id = R.id.tabMain
                tabMode = TabLayout.MODE_FIXED
                tabGravity = TabLayout.GRAVITY_FILL
            }.lparams(width = matchParent, height = wrapContent)
            viewPager {
                id = R.id.viewPagerMain
            }.lparams(width = matchParent, height = wrapContent)
        }

    }
}
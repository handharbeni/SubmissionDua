package com.mhandharbeni.submissiondua.fragment.ui

import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class OverviewUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        frameLayout {
            nestedScrollView {
                textView {
                    id = R.id.txtOverview
                }.lparams(matchParent, wrapContent){
                    margin = dip(8)
                }
            }.lparams(matchParent, wrapContent)
        }
    }
}
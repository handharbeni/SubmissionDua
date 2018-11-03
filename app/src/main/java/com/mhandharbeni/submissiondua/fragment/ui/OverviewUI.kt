package com.mhandharbeni.submissiondua.fragment.ui

import android.view.Gravity
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
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
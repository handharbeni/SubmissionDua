package com.mhandharbeni.submissiondua.fragment.ui

import android.view.Gravity
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PlayerUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent){
                margin = dip(8)
                padding = dip(8)
            }
            id = R.id.ItemCvParent
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                imageView {
                    id = R.id.ItemIvLogo
                }.lparams {
                    width = dip(80)
                    height = dip(80)
                    padding = dip(8)
                }
                textView("NAMA PLAYER") {
                    id = R.id.ItemTxtNama
                    gravity = Gravity.CENTER_VERTICAL
                    textColor = R.color.colorAccent
                }.lparams(width = matchParent, height = wrapContent){
                    padding=dip(15)
                }
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
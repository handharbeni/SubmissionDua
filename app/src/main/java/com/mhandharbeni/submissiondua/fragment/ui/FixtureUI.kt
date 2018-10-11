package com.mhandharbeni.submissiondua.fragment.ui

import android.view.Gravity
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FixtureUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent){
                margin = dip(10)
                padding = dip(15)
            }
            id = R.id.ItemCvParent
            linearLayout {
                orientation = LinearLayout.VERTICAL
                textView("TANGGAL") {
                    id = R.id.ItemTxtTanggal
                    gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
                    textColor = R.color.colorAccent
                }.lparams(width = matchParent, height = wrapContent){
                    padding=dip(15)
                }
                linearLayout {
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL
                    textView("HOME") {
                        gravity=Gravity.END
                        id = R.id.ItemTxtHome
                        padding = dip(5)
                    }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    linearLayout {
                        backgroundColor=resources.getColor(R.color.grey)
                        gravity=Gravity.CENTER
                        textView("2") {
                            id = R.id.ItemTxtHomeScore
                            padding = dip(3)
                        }.lparams(width = wrapContent, height = wrapContent)
                        textView("VS") {
                            padding = dip(3)
                        }.lparams(width = wrapContent, height = wrapContent)
                        textView("0") {
                            padding = dip(3)
                            id=R.id.ItemTxtAwayScore
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = 0, height = wrapContent, weight = 0.2f)
                    textView("AWAY") {
                        gravity=Gravity.START
                        id=R.id.ItemTxtAway
                        padding = dip(5)
                    }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                }.lparams(width = matchParent, height = wrapContent){
                    weightSum = 1.0f
                }
            }.lparams(width = matchParent, height = wrapContent){
                padding = dip(10)
            }
        }
    }
}
package com.mhandharbeni.submissiondua.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class DetailTeamActivityUI<T>: AnkoComponent<T> {

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        relativeLayout {
            floatingActionButton {
//                alignPar
                translationY = -15.0f
                id = R.id.btnFavourite
                setImageResource(R.drawable.ic_unfavourite)
                backgroundTintMode = PorterDuff.Mode.SRC
            }.lparams(width= wrapContent, height = wrapContent){
                gravity=Gravity.TOP
                margin=dip(5)
            }
            scrollView{
                lparams(width = matchParent, height = wrapContent)
                id = R.id.ItemCvParent
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        background = ColorDrawable(Color.BLUE)
                        imageView {
                            id = R.id.ivLogoTeam

                        }.lparams {
                            width = dip(100)
                            height = dip(100)
                            gravity = Gravity.CENTER_HORIZONTAL
                            margin = dip (8)
                        }
                        textView {
                            id = R.id.ItemTxtNama
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.WHITE
                        }.lparams(matchParent, wrapContent)
                        textView {
                            id = R.id.ItemTxtTahun
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.WHITE
                        }.lparams(matchParent, wrapContent)
                        textView {
                            id = R.id.ItemTxtStadium
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.WHITE
                        }.lparams(matchParent, wrapContent){
                            bottomMargin = dip(8)
                        }
                    }.lparams(matchParent, wrapContent)

                    appBarLayout {
                        tabLayout {
                            id = R.id.tabTeam
                        }.lparams(matchParent, wrapContent)
                    }.lparams(matchParent, wrapContent)
                    viewPager {
                        id = R.id.viewPager
                    }.lparams(matchParent, wrapContent)
                    frameLayout {
                        id = R.id.frameTeam
                    }.lparams(matchParent, wrapContent)

                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }
}
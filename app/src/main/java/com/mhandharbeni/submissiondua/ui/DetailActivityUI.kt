package com.mhandharbeni.submissiondua.ui

import android.graphics.PorterDuff
import android.view.Gravity
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class DetailActivityUI<T>: AnkoComponent<T> {

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        relativeLayout {
            floatingActionButton {
                id = R.id.btnFavourite
                setImageResource(R.drawable.ic_unfavourite)
                backgroundTintMode = PorterDuff.Mode.SRC
            }.lparams(width= wrapContent, height = wrapContent){
                alignParentBottom()
                alignParentRight()
                gravity=Gravity.BOTTOM
                margin=dip(5)
            }
            scrollView{
                lparams(width = matchParent, height = wrapContent)
                id = R.id.ItemCvParent
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    /*score*/
                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            imageView {
                                setImageResource(R.drawable.ic_football)
                                id = R.id.DetailIMGHome
                                padding = dip(5)
                                this@linearLayout.gravity = Gravity.RIGHT
                            }.lparams(width= matchParent, height = wrapContent)
                            textView ("HOME"){
                                gravity = Gravity.RIGHT
                                id = R.id.detailTXTHome
                                padding = dip(5)
                            }
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity= Gravity.CENTER
                            textView("2") {
                                id = R.id.DetailItemTxtHomeScore
                                padding = dip(3)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("VS") {
                                padding = dip(3)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView("0") {
                                padding = dip(3)
                                id=R.id.DetailItemTxtAwayScore
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(width = 0, height = wrapContent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            imageView {
                                setImageResource(R.drawable.ic_football)
                                id = R.id.DetailIMGAway
                                padding = dip(5)
                            }.lparams(width= matchParent, height = wrapContent)
                            textView ("HOME"){
                                id = R.id.detailTXTAway
                                padding = dip(5)
                            }
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*score*/
                    /*goal*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        textView("GOAL HOME"){
                            gravity = Gravity.RIGHT
                            id = R.id.detailGoalHome
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView("GOAL") {
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        textView("GOAL AWAY"){
                            gravity = Gravity.LEFT
                            id = R.id.detailGoalAway
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*goal*/
                    /*lineup*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*GoalKeepers*/
                            textView("Goal Keepers"){
                                gravity = Gravity.RIGHT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKHome
                                gravity = Gravity.RIGHT
                            }
                            /*GoalKeepers*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView("LINEUP") {
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*GoalKeepers*/
                            textView("Goal Keepers"){
                                gravity = Gravity.LEFT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKAway
                                gravity = Gravity.LEFT
                            }
                            /*GoalKeepers*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*lineup*/
                    /*lineup*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*Defenders*/
                            textView("Defenders"){
                                gravity = Gravity.RIGHT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKDefendersHome
                                gravity = Gravity.RIGHT
                            }
                            /*Defenders*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView{
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*Defenders*/
                            textView("Defenders"){
                                gravity = Gravity.LEFT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKDefendersAway
                                gravity = Gravity.LEFT
                            }
                            /*Defenders*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*lineup*/
                    /*lineup*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*Midfielders*/
                            textView("Mildfielder"){
                                gravity = Gravity.RIGHT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKMildfielderHome
                                gravity = Gravity.RIGHT
                            }
                            /*Midfielders*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView{
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*Midfielders*/
                            textView("Mildfielder"){
                                gravity = Gravity.LEFT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKMildfielderAway
                                gravity = Gravity.LEFT
                            }
                            /*Midfielders*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*lineup*/
                    /*lineup*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*forwards*/
                            textView("Forwards"){
                                gravity = Gravity.RIGHT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKForwardsHome
                                gravity = Gravity.RIGHT
                            }
                            /*forwards*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView{
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            /*forwards*/
                            textView("Forwards"){
                                gravity = Gravity.LEFT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKForwardsAway
                                gravity = Gravity.LEFT
                            }
                            /*forwards*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*lineup*/
                    /*lineup*/
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            textView("Substitues"){
                                gravity = Gravity.RIGHT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKSubstituesHome
                                gravity = Gravity.RIGHT
                            }
                            /*subtitues*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL
                            textView{
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(3)
                            }.lparams(width = matchParent, height = matchParent)
                        }.lparams(width = 0, height = matchParent, weight = 0.2f)
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            textView("Substitues"){
                                gravity = Gravity.LEFT
                                textSize=10.0f
                            }
                            textView{
                                id = R.id.detailLUGKSubstituesAway
                                gravity = Gravity.LEFT
                            }
                            /*subtitues*/
                        }.lparams(width = 0, height = wrapContent, weight = 0.4f)
                    }.lparams(width = matchParent, height = wrapContent){
                        weightSum = 1.0f
                    }
                    /*lineup*/
                }.lparams(width = matchParent, height = wrapContent){
                    padding = dip(10)
                }
            }
        }
    }
}
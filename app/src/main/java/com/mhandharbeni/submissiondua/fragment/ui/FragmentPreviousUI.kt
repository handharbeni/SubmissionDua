package com.mhandharbeni.submissiondua.fragment.ui

import android.graphics.PorterDuff
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FragmentPreviousUI<T>: AnkoComponent<T> {

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        relativeLayout {
            lparams(matchParent, wrapContent)
            floatingActionButton {
                id = R.id.btnSearch
                setImageResource(R.drawable.ic_search)
                backgroundTintMode = PorterDuff.Mode.SRC
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.TOP
                margin = dip(5)
            }
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner {
                    id = R.id.spinner
                }

                swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        recyclerView {
                            id = R.id.rvScorePrevious
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

//                    progressBar {
//                        id = R.id.progressBar
//                    }.lparams{
//                        centerHorizontally()
//                    }
                    }
                }
            }
        }

    }
}
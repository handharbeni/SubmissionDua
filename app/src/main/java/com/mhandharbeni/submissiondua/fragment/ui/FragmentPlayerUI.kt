package com.mhandharbeni.submissiondua.fragment.ui

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.mhandharbeni.submissiondua.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FragmentPlayerUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            recyclerView {
                id = R.id.rvPlayer
                lparams(matchParent, wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }

        }
    }
}
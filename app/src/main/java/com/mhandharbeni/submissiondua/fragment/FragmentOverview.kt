package com.mhandharbeni.submissiondua.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.OverviewUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx

class FragmentOverview: Fragment() {
    private lateinit var txtOverview:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View
        v = OverviewUI<FragmentOverview>().createView(AnkoContext.create(ctx, this))
        val intent = activity!!.intent
        var deskripsi = intent.getStringExtra("deskripsi")
        txtOverview = v.find(R.id.txtOverview)
        txtOverview.text = deskripsi
        return v
    }

}
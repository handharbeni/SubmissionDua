package com.mhandharbeni.submissiondua.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.adapter.PagerAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentMatchesUI
import com.mhandharbeni.submissiondua.fragment.ui.FragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx

class MatchesFragment : Fragment() {

//    private var listFixtures: MutableList<EventsItem> = mutableListOf()
//    private lateinit var presenter: MainPresenter
//    private lateinit var adapter: MainAdapter
//
//    private lateinit var rvScore: RecyclerView
//    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var v:View
    private lateinit var tabMain: TabLayout
    private lateinit var viewPager: ViewPager
//
//    private lateinit var request:ApiRepository
//    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = FragmentMatchesUI<MatchesFragment>().createView(AnkoContext.create(ctx, this))
        viewPager = v.find(R.id.viewPagerMain)
        tabMain = v.find(R.id.tabMain)
        val fragmentAdapter = PagerAdapter(childFragmentManager)

        viewPager.adapter = fragmentAdapter
        tabMain.setupWithViewPager(viewPager)
        return  v
    }

}

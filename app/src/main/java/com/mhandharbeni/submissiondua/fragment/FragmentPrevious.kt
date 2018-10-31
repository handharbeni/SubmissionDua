package com.mhandharbeni.submissiondua.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.BuildConfig
import com.mhandharbeni.submissiondua.DetailActivity
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.adapter.MainAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentPreviousUI
import com.mhandharbeni.submissiondua.fragment.ui.FragmentUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FragmentPrevious: Fragment(), MainView {
    override fun showLastFixtures(data: List<EventsItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }


    private var listFixtures: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    private lateinit var rvScore: RecyclerView
    //    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var v:View

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = FragmentPreviousUI<FragmentPrevious>().createView(AnkoContext.create(ctx, this))

        rvScore = v.find(R.id.rvScorePrevious)
//        progressBar = v.find(R.id.progressBar)
        swipeRefresh = v.find(R.id.swipeRefresh)

        request = ApiRepository()
        gson = Gson()
        presenter = MainPresenter(this, request, gson)

//        adapter = MainAdapter(listFixtures)
        adapter = MainAdapter(listFixtures){partItem:EventsItem?->clickFixtures(partItem)}
        rvScore.adapter = adapter


        swipeRefresh.onRefresh {
            presenter.getFixturesList(BuildConfig.PREVIOUS)
        }
        presenter.getFixturesList(BuildConfig.PREVIOUS)
        return v
    }
    override fun showLoading() {
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
        }
    }

    override fun hideLoading() {
        swipeRefresh.handler.post {
            swipeRefresh.isRefreshing = false
        }
    }
    override fun showFixtures(data: List<EventsItem>?) {
        swipeRefresh.isRefreshing = false
        listFixtures.clear()
        data?.let { listFixtures.addAll(it) }

        adapter.notifyDataSetChanged()

    }
    private fun clickFixtures(eventsItem: EventsItem?){
        ctx.startActivity<DetailActivity>("id" to eventsItem?.idEvent)
    }
}


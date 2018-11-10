package com.mhandharbeni.submissiondua.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.BuildConfig
import com.mhandharbeni.submissiondua.DetailActivity
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.SearchActivity
import com.mhandharbeni.submissiondua.adapter.MainAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class FragmentNext: Fragment(), MainView {
    override fun showTeams(data: List<TeamsItem>?) {
    }

    override fun showPlayer(data: List<PlayerItem>?) {
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }


    private var listFixtures: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    private lateinit var rvScore: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var search: ImageView
    private lateinit var v:View

    private lateinit var request:ApiRepository
    private lateinit var gson: Gson

    private lateinit var idLeague:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = FragmentUI<FragmentNext>().createView(AnkoContext.create(ctx, this))


        rvScore = v.find(R.id.rvScore)
        swipeRefresh = v.find(R.id.swipeRefresh)
        spinner = v.find(R.id.spinner)
        search = v.find(R.id.btnSearch)

        request = ApiRepository()
        gson = Gson()
        presenter = MainPresenter(this, request, gson)

        adapter = MainAdapter(listFixtures){partItem:EventsItem?->clickFixtures(partItem)}
        rvScore.adapter = adapter

        val spinnerItems = resources.getStringArray(R.array.league)
        val  spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                idLeague = when (spinner.selectedItemPosition){
                    0 -> "4328"
                    1 -> "4331"
                    2 -> "4332"
                    3 -> "4334"
                    else -> "4335"
                }
                presenter.getFixturesList(BuildConfig.NEXT, idLeague)
            }
        }

        search.onClick {
            ctx.startActivity<SearchActivity>("search" to "EVENT")
        }

        swipeRefresh.onRefresh {
            presenter.getFixturesList(BuildConfig.NEXT, idLeague)
        }
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
        listFixtures.clear()
        data?.let { listFixtures.addAll(it) }

        adapter.notifyDataSetChanged()

    }

    private fun clickFixtures(eventsItem: EventsItem?){
        toast(getString(R.string.belum_bertanding))
        ctx.startActivity<DetailActivity>("id" to eventsItem?.idEvent)
    }
}
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
import com.mhandharbeni.submissiondua.*
import com.mhandharbeni.submissiondua.R.array.league
import com.mhandharbeni.submissiondua.adapter.TeamAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentTeamUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class FragmentTeam: Fragment(), MainView {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeams(data: List<TeamsItem>?) {
        swipeRefresh.isRefreshing = false
        listTeams.clear()
        data?.let { listTeams.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    override fun showPlayer(data: List<PlayerItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var listTeams: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: TeamAdapter

    private lateinit var rvTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var search: ImageView
    private lateinit var v:View

    private lateinit var liga:String

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = FragmentTeamUI<FragmentTeam>().createView(AnkoContext.create(ctx, this))
        rvTeam = v.find(R.id.rvTeam)
        swipeRefresh = v.find(R.id.swipeRefresh)
        spinner = v.find(R.id.spinner)
        search = v.find(R.id.btnSearch)

        request = ApiRepository()
        gson = Gson()
        presenter = MainPresenter(this, request, gson)

        adapter = TeamAdapter(listTeams){partItem:TeamsItem?->clickTeam(partItem)}
        rvTeam.adapter = adapter

        val spinnerItems = resources.getStringArray(league)
        val  spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var name = spinner.selectedItem.toString()
                var result:List<String> = name.split(" ")
                liga = result.joinToString("%20")
                presenter.getTeam(liga)
            }
        }


        search.onClick {
            ctx.startActivity<SearchActivity>("search" to "TEAM")
        }

        swipeRefresh.onRefresh {
            presenter.getTeam(liga)
        }

        return  v
    }

    private fun clickTeam(teamsItem: TeamsItem?){
        ctx.startActivity<DetailTeamActivity>("idTeam" to teamsItem?.idTeam, "deskripsi" to teamsItem?.strDescriptionEN)
    }
}
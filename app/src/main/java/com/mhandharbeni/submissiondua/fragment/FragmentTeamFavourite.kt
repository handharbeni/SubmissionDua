package com.mhandharbeni.submissiondua.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mhandharbeni.submissiondua.DetailTeamActivity
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.adapter.FavouriteTeamAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentFavouriteUI
import com.mhandharbeni.submissiondua.fragment.ui.FragmentFavouriteUITeam
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.model.sqlite.TeamFavouriteTable
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FragmentTeamFavourite: Fragment(), MainView {
    override fun showTeams(data: List<TeamsItem>?) {
    }

    override fun showPlayer(data: List<PlayerItem>?) {
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
    }

    override fun showFixtures(data: List<EventsItem>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }

    private var listFavourite: MutableList<TeamFavouriteTable> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FavouriteTeamAdapter

    private lateinit var rvScore: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var v: View

    private val database: SqliteFavourite? get() = SqliteFavourite.getInstance(ctx)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = FragmentFavouriteUITeam<FragmentTeamFavourite>().createView(AnkoContext.create(ctx, this))


        rvScore = v.find(R.id.rvScoreFavouriteTeam)
        swipeRefresh = v.find(R.id.swipeRefresh)

        presenter = MainPresenter(this, null, null)

        adapter = FavouriteTeamAdapter(listFavourite){partItem:TeamFavouriteTable?->clickFixtures(partItem)}
        rvScore.adapter = adapter

        showFavourite()

        swipeRefresh.onRefresh {
            showFavourite()
        }
        return v
    }


    private fun showFavourite() {
        listFavourite.clear()
        database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(TeamFavouriteTable.TABLE_NAME)
            val favorite = result.parseList(classParser<TeamFavouriteTable>())
            listFavourite.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
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

    private fun clickFixtures(favourite: TeamFavouriteTable?){
        ctx.startActivity<DetailTeamActivity>("idTeam" to favourite?.idTeam, "deskripsi" to favourite?.deskripsi)
    }

    override fun onResume() {
        super.onResume()
        showFavourite()
    }
}
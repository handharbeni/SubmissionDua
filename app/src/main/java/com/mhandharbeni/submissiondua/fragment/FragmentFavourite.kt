package com.mhandharbeni.submissiondua.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mhandharbeni.submissiondua.BuildConfig
import com.mhandharbeni.submissiondua.DetailActivity
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.adapter.FavouriteAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class FragmentFavourite: Fragment(), MainView {
    override fun showFixtures(data: List<EventsItem>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }

    private var listFavourite: MutableList<FavouriteTable> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FavouriteAdapter

    private lateinit var rvScore: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var v: View

    private val database: SqliteFavourite? get() = SqliteFavourite.getInstance(ctx)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = FragmentUI<FragmentFavourite>().createView(AnkoContext.create(ctx, this))


        rvScore = v.find(R.id.rvScore)
        swipeRefresh = v.find(R.id.swipeRefresh)

        presenter = MainPresenter(this, null, null, database)

        adapter = FavouriteAdapter(listFavourite){partItem:FavouriteTable?->clickFixtures(partItem)}
        rvScore.adapter = adapter


        swipeRefresh.onRefresh {
            presenter.getTeamFavourite()
        }
        return v
    }


    override fun showFavourite(data: List<FavouriteTable>?) {
        listFavourite.clear()
        data?.let { listFavourite.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
        }
    }
    override fun onStart() {
        super.onStart()
        presenter.getFixturesList(BuildConfig.NEXT)
    }
    override fun hideLoading() {
        swipeRefresh.handler.post {
            swipeRefresh.isRefreshing = false
        }
    }

    private fun clickFixtures(favourite: FavouriteTable?){
//        toast("Pertandingan Belum Berlangsung!!")
        val intent = DetailActivity.newIntent(ctx, favourite)
        ctx.startActivity(intent)
    }
}
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
import com.mhandharbeni.submissiondua.fragment.ui.FragmentFavouriteUI
import com.mhandharbeni.submissiondua.fragment.ui.FragmentUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

class FragmentFavourite: Fragment(), MainView {
    override fun showLastFixtures(data: List<EventsItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        v = FragmentFavouriteUI<FragmentFavourite>().createView(AnkoContext.create(ctx, this))


        rvScore = v.find(R.id.rvScoreFavourite)
        swipeRefresh = v.find(R.id.swipeRefresh)

        presenter = MainPresenter(this, null, null)

        adapter = FavouriteAdapter(listFavourite){partItem:FavouriteTable?->clickFixtures(partItem)}
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
            val result = select(FavouriteTable.TABLE_NAME)
            val favorite = result.parseList(classParser<FavouriteTable>())
            listFavourite.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
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
        ctx.startActivity<DetailActivity>("id" to favourite?.idFixtures)
    }

    override fun onResume() {
        super.onResume()
        showFavourite()
    }
}
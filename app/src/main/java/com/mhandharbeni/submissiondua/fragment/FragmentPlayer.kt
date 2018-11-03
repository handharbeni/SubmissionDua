package com.mhandharbeni.submissiondua.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.DetailPlayerActivity
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.adapter.PlayerAdapter
import com.mhandharbeni.submissiondua.fragment.ui.FragmentPlayerUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class FragmentPlayer : Fragment(), MainView {
    override fun showLoading() {
    }

    override fun hideLoading() {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPlayer(data: List<PlayerItem>?) {
        listPlayers.clear()
        data?.let { listPlayers.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    private var listPlayers: MutableList<PlayerItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: PlayerAdapter

    private lateinit var rvPlayer: RecyclerView
    private lateinit var v:View

    private lateinit var idTeam:String

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = FragmentPlayerUI<FragmentPlayer>().createView(AnkoContext.create(ctx, this))

        val intent = act.intent
        idTeam = intent.getStringExtra("idTeam")

        rvPlayer = v.find(R.id.rvPlayer)

        request = ApiRepository()
        gson = Gson()
        presenter = MainPresenter(this, request, gson)

        adapter = PlayerAdapter(listPlayers){
            ctx.startActivity<DetailPlayerActivity>("player" to it)
        }
        rvPlayer.adapter = adapter

        presenter.getPlayer(idTeam)

        return v
    }


}

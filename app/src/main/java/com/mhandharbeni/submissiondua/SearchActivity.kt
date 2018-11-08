package com.mhandharbeni.submissiondua

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.adapter.MainAdapter
import com.mhandharbeni.submissiondua.adapter.TeamAdapter
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity(), MainView, AnkoLogger {
    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showFixtures(data: List<EventsItem>?) {
        events.clear()
        data?.let { events.addAll(it) }
        info("INFO EVENT ${events.size}")
        eventAdapter.notifyDataSetChanged()
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }

    override fun showTeams(data: List<TeamsItem>?) {
        teams.clear()
        data?.let { teams.addAll(it) }
        info("INFO TEAM ${teams.size}")
        teamAdapter.notifyDataSetChanged()
    }

    override fun showPlayer(data: List<PlayerItem>?) {
    }

    private lateinit var presenter: MainPresenter
    private lateinit var eventAdapter: MainAdapter
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var search:String
    private lateinit var rvSearch: RecyclerView
    private lateinit var searchView: SearchView

    private var events: MutableList<EventsItem> = mutableListOf()
    private var teams: MutableList<TeamsItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        rvSearch = find(R.id.rvSearch)
        searchView = find(R.id.searchView)

        val intent = intent
        search = intent.getStringExtra("search")

        if (search.contentEquals("EVENT")){
            event()
        } else if (search.contentEquals("TEAM")) {
            team()
        }
    }

    private fun event(){
        eventAdapter = MainAdapter(events){
            startActivity<DetailActivity>("id" to it?.idEvent)
        }
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.adapter = eventAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getFixturesSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getFixturesSearch(newText)
                return true
            }

        })
    }

    private fun team(){
        teamAdapter = TeamAdapter(teams){
            startActivity<DetailTeamActivity>("idTeam" to it?.idTeam, "deskripsi" to it?.strDescriptionEN)
        }
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.adapter = teamAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getTeamSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getTeamSearch(newText)
                return false
            }

        })
    }
}

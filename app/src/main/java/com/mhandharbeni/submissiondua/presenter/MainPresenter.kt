package com.mhandharbeni.submissiondua.presenter

import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.PlayerResponse
import com.mhandharbeni.submissiondua.model.Response
import com.mhandharbeni.submissiondua.model.TeamResponse
import com.mhandharbeni.submissiondua.model.sqlite.SearchResponse
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.CoroutineContextProvider
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.tools.TheSportDBApi
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository?,
                    private val gson: Gson?,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFixturesList(state: String?, league:String?) {
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixtures(state, league)), Response::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.events)
        }
    }

    fun getDetailFixtures(id: String?) {
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixturesDetail(id)), Response::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.events)
        }
    }

    fun getTeamDetail(id: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeamDetail(id)), TeamResponse::class.java)
            }

            view.hideLoading()
            view.showTeams(data.await()?.teams)
        }
    }

    fun getTeam(liga: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeam(liga)), TeamResponse::class.java)
            }

            view.hideLoading()
            view.showTeams(data.await()?.teams)
        }
    }

    fun getDetailTeam(id: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeamDetail(id)), TeamResponse::class.java)
            }

            view.hideLoading()
            view.showTeams(data.await()?.teams)
        }
    }

    fun getPlayer(team: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getPlayer(team)), PlayerResponse::class.java)
            }

            view.hideLoading()
            view.showPlayer(data.await()?.player)
        }
    }

    fun getFixturesSearch(search: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixturesSearch(search)), SearchResponse::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.event)
        }
    }

    fun getTeamSearch(search: String?){
        view.showLoading()
        launch(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeamSearch(search)), TeamResponse::class.java)
            }

            view.hideLoading()
            view.showTeams(data.await()?.teams)
        }
    }
}
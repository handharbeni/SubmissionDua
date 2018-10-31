package com.mhandharbeni.submissiondua.presenter

import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.Response
import com.mhandharbeni.submissiondua.model.TeamDetail
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.CoroutineContextProvider
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.tools.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository?,
                    private val gson: Gson?,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFixturesList(state: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixtures(state)), Response::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.events)
        }
    }
    fun getLastFixturesList(state: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getlastFixtures(state)), Response::class.java)
            }

            view.hideLoading()
            view.showLastFixtures(data.await()?.events)
        }
    }

    fun getDetailFixtures(id: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixturesDetail(id)), Response::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.events)
        }
    }

    fun getTeamDetail(id: String?, status: String){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeamDetail(id)), Response::class.java)
            }

            view.hideLoading()
            view.showFixtures(data.await()?.events)
        }
    }
}
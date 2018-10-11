package com.mhandharbeni.submissiondua.presenter

import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.Response
import com.mhandharbeni.submissiondua.model.TeamDetail
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.tools.TheSportDBApi
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository?,
                    private val gson: Gson?,
                    private val database: SqliteFavourite?) {

    fun getFixturesList(state: String?) {
        view.showLoading()
        doAsync {
            val data = gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getFixtures(state)),
                    Response::class.java
            )
            uiThread {
                view.hideLoading()
                view.showFixtures(data?.events)
            }
        }
    }

    fun getTeamDetail(id: String?, status: String){
        view.showLoading()
        doAsync {
            val data = gson?.fromJson(apiRepository?.doRequest(TheSportDBApi.getTeamDetail(id)),
                    TeamDetail::class.java
            )
            uiThread {
                view.hideLoading()
                view.showDetail(data?.teams, status)
            }
        }
    }

    fun getTeamFavourite(){
        val rowParser = classParser<FavouriteTable>()
        view.showLoading()
        doAsync {
            var data: List<FavouriteTable>? = null
            database?.use{
                select(FavouriteTable.TABLE_NAME).exec {
                    data = parseList(rowParser).toList()
                }
            }
            view.run {
                hideLoading()
                showFavourite(data)
            }
        }
    }
}
package com.mhandharbeni.submissiondua.tools

import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showFixtures(data: List<EventsItem>?)
    fun showLastFixtures(data: List<EventsItem>?)
    fun showDetail(data: List<TeamsItem>?, status: String)
    fun showTeams(data: List<TeamsItem>?)
    fun showPlayer(data: List<PlayerItem>?)
}
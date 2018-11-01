package com.mhandharbeni.submissiondua.tools

import android.net.Uri
import com.mhandharbeni.submissiondua.BuildConfig


object TheSportDBApi {
    fun getFixtures(state: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/$state?id=4328"

//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath(state)
//                .appendQueryParameter("id", "4328")
//                .build()
//                .toString()
    }
    fun getlastFixtures(state: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/$state?id=4328"

//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath(state)
//                .appendQueryParameter("id", "4328")
//                .build()
//                .toString()
    }
    fun getFixturesDetail(id: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php/?id=$id"
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("lookupevent.php")
//                .appendQueryParameter("id", id)
//                .build()
//                .toString()
    }
    fun getTeamDetail(id: String?): String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php/?id=$id"
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("lookupteam.php")
//                .appendQueryParameter("id", id)
//                .build()
//                .toString()
    }

    fun getTeam(liga:String?):String{
        return "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=$liga"
    }

    fun getPlayer(id:String?):String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=$id"
    }

    fun getFixturesSearch(fixture:String?):String{
        return "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=$fixture"
    }

    fun getTeamSearch(team:String?):String{
        return "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=$team"
    }
}
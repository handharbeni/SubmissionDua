package com.mhandharbeni.submissiondua.tools

import android.net.Uri
import com.mhandharbeni.submissiondua.BuildConfig


object TheSportDBApi {
    fun getFixtures(state: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(state)
                .appendQueryParameter("id", "4328")
                .build()
                .toString()
    }
    fun getTeamDetail(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
}
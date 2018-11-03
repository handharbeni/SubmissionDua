package com.mhandharbeni.submissiondua.model.sqlite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamFavouriteTable(
        val id: Long?,
        val idTeam:String?,
        val titleTeam:String?,
        val logoTeam:String?,
        val formedYear:String?,
        val stadium:String?,
        val deskripsi:String?) : Parcelable {

    companion object {
        const val TABLE_NAME = "TeamFavourite"
        const val ID: String = "ID_"
        const val FIELD_ID_TEAM = "ID_TEAM"
        const val FIELD_TITLE_TEAM = "TITLE_TEAM"
        const val FIELD_LOGO_TEAM = "LOGO_TEAM"
        const val FIELD_FORMED_YEAR = "FORMED_YEAR"
        const val FIELD_STADIUM = "STADIUM"
        const val FIELD_DESKRIPSI = "DESKRIPSI"
    }
}
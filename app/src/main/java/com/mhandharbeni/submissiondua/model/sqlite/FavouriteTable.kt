package com.mhandharbeni.submissiondua.model.sqlite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavouriteTable(
        val idFixtures:String,
        val date:String,
        val titleHome:String,
        val titleAway:String,
        val scoreHome:String,
        val scoreAway:String) : Parcelable {
    companion object {
        const val TABLE_NAME = "MFavourite"
        const val FIELD_DATE_FIXTURES = "DATE_FIXTURES"
        const val FIELD_ID_FIXTURES = "ID_FIXTURES"
        const val FIELD_TITLE_HOME = "TITLE_HOME"
        const val FIELD_TITLE_AWAY = "TITLE_AWAY"
        const val FIELD_SCORE_HOME = "SCORE_HOME"
        const val FIELD_SCORE_AWAY = "SCORE_AWAY"
    }
}
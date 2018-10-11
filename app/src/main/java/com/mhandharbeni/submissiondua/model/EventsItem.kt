package com.mhandharbeni.submissiondua.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(

	@SerializedName("intHomeShots")
	val intHomeShots: Int? = null,

	@SerializedName("strSport")
	val strSport: String? = null,

	@SerializedName("strHomeLineupDefense")
	val strHomeLineupDefense: String? = null,

	@SerializedName("strAwayLineupSubstitutes")
	val strAwayLineupSubstitutes: String? = null,

	@SerializedName("idLeague")
	val idLeague: String? = null,

	@SerializedName("idSoccerXML")
	val idSoccerXML: String? = null,

	@SerializedName("strHomeLineupForward")
	val strHomeLineupForward: String? = null,

	@SerializedName("strTVStation")
	val strTVStation: String? = null,

	@SerializedName("strHomeGoalDetails")
	val strHomeGoalDetails: String? = null,

	@SerializedName("strAwayLineupGoalkeeper")
	val strAwayLineupGoalkeeper: String? = null,

	@SerializedName("strAwayLineupMidfield")
	val strAwayLineupMid: String? = null,

	@SerializedName("idEvent")
	val idEvent: String? = null,

	@SerializedName("intRound")
	val intRound: String? = null,

	@SerializedName("strHomeYellowCards")
	val strHomeYellowCards: String? = null,

	@SerializedName("idHomeTeam")
	val idHomeTeam: String? = null,

	@SerializedName("intHomeScore")
	val intHomeScore: String? = null,

	@SerializedName("dateEvent")
	val dateEvent: String? = null,

	@SerializedName("strCountry")
	val strCountry: String? = null,

	@SerializedName("strAwayTeam")
	val strAwayTeam: String? = null,

	@SerializedName("strHomeLineupMidfield")
	val strHomeLineupMidfield: String? = null,

	@SerializedName("strDate")
	val strDate: String? = null,

	@SerializedName("strHomeFormation")
	val strHomeFormation: String? = null,

	@SerializedName("strMap")
	val strMap: String? = null,

	@SerializedName("idAwayTeam")
	val idAwayTeam: String? = null,

	@SerializedName("strAwayRedCards")
	val strAwayRedCards: String? = null,

	@SerializedName("strBanner")
	val strBanner: String? = null,

	@SerializedName("strFanart")
	val strFanart: String? = null,

	@SerializedName("strDescriptionEN")
	val strDescriptionEN: String? = null,

	@SerializedName("strResult")
	val strResult: String? = null,

	@SerializedName("strCircuit")
	val strCircuit: String? = null,

	@SerializedName("intAwayShots")
	val intAwayShots: Int? = null,

	@SerializedName("strFilename")
	val strFilename: String? = null,

	@SerializedName("strTime")
	val strTime: String? = null,

	@SerializedName("strAwayGoalDetails")
	val strAwayGoalDetails: String? = null,

	@SerializedName("strAwayLineupForward")
	val strAwayLineupForward: String? = null,

	@SerializedName("strLocked")
	val strLocked: String? = null,

	@SerializedName("strSeason")
	val strSeason: String? = null,

	@SerializedName("intSpectators")
	val intSpectators: Int? = null,

	@SerializedName("strHomeRedCards")
	val strHomeRedCards: String? = null,

	@SerializedName("strHomeLineupGoalkeeper")
	val strHomeLineupGoalkeeper: String? = null,

	@SerializedName("strHomeLineupSubstitutes")
	val strHomeLineupSubstitutes: String? = null,

	@SerializedName("strAwayFormation")
	val strAwayFormation: String? = null,

	@SerializedName("strEvent")
	val strEvent: String? = null,

	@SerializedName("strAwayYellowCards")
	val strAwayYellowCards: String? = null,

	@SerializedName("strAwayLineupDefense")
	val strAwayLineupDefense: String? = null,

	@SerializedName("strHomeTeam")
	val strHomeTeam: String? = null,

	@SerializedName("strThumb")
	val strThumb: String? = null,

	@SerializedName("strLeague")
	val strLeague: String? = null,

	@SerializedName("intAwayScore")
	val intAwayScore: String? = null,

	@SerializedName("strCity")
	val strCity: String? = null,

	@SerializedName("strPoster")
	val strPoster: String? = null
) : Parcelable
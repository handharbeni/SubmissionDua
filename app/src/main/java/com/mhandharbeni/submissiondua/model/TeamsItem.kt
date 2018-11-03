package com.mhandharbeni.submissiondua.model

import com.google.gson.annotations.SerializedName

data class TeamsItem(

	@SerializedName("idTeam")
	val idTeam: String? = null,

	@SerializedName("strTeam")
	val strTeam: String? = null,

	@SerializedName("strTeamBadge")
	val strTeamBadge: String? = null,

	@SerializedName("intFormedYear")
	val intFormedYear: String? = null,

	@SerializedName("strStadium")
	val strStadium: String? = null,

	@SerializedName("strDescriptionEN")
	val strDescriptionEN: String? = null
)
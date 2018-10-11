package com.mhandharbeni.submissiondua.model

import com.google.gson.annotations.SerializedName

data class TeamDetail(
	@SerializedName("teams")
	val teams: List<TeamsItem> = mutableListOf()
)
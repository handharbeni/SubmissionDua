package com.mhandharbeni.submissiondua.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(

	@SerializedName("player")
	val player: List<PlayerItem> = mutableListOf()
)
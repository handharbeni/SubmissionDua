package com.mhandharbeni.submissiondua.model

import com.google.gson.annotations.SerializedName

data class Response(

	@SerializedName("events")
	val events: List<EventsItem> = mutableListOf()
)
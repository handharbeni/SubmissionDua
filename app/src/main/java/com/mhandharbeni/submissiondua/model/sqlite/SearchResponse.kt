package com.mhandharbeni.submissiondua.model.sqlite

import com.google.gson.annotations.SerializedName
import com.mhandharbeni.submissiondua.model.EventsItem

data class SearchResponse(

	@SerializedName("event")
	val event: List<EventsItem> = mutableListOf()
)
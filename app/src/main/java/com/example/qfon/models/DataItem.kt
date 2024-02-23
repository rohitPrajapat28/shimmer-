package com.example.qfon.models

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("trips")
	val trips: Int,

	@field:SerializedName("__v")
	val v: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("airline")
	val airline: List<AirlineItem>
)
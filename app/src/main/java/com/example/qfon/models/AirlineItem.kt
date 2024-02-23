package com.example.qfon.models

import com.google.gson.annotations.SerializedName

data class AirlineItem(

	@field:SerializedName("established")
	val established: String,

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("head_quaters")
	val headQuaters: String,

	@field:SerializedName("logo")
	val logo: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slogan")
	val slogan: String
)
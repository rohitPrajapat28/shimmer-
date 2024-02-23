package com.example.qfon.models

import com.google.gson.annotations.SerializedName

data class PassengerData(

	@field:SerializedName("totalPassengers")
	val totalPassengers: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("totalPages")
	val totalPages: Int
)
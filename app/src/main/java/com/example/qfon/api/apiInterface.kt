package com.example.qfon.api

import android.graphics.pdf.PdfDocument.Page
import com.example.qfon.models.PassengerData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.Objects

interface apiInterface {

    @GET("/v1/passenger")
    fun getData(@QueryMap filter: HashMap<String,String>) : Call<PassengerData>
}
package com.example.qfon.activity

import android.app.appsearch.SearchResult
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.qfon.R
import com.example.qfon.adapter.PasengerAdapter
import com.example.qfon.api.apiInterface
import com.example.qfon.databinding.ActivityMainBinding
import com.example.qfon.models.AirlineItem
import com.example.qfon.models.DataItem
import com.example.qfon.models.PassengerData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pasengerAdapter: PasengerAdapter
    private lateinit var dataItem: List<DataItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.primary_color)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getData()

        binding.headerview.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }


    private fun filterList(query: String?) {
        if (query != null) {
            val searchResult = ArrayList<DataItem>()
            for (i in dataItem) {
                if (i.name.lowercase().contains(query)) {
                    searchResult.add(i)
                }
            }
            if (searchResult.isEmpty()){
                Toast.makeText(this,"NO DATA FOUND",Toast.LENGTH_SHORT).show()
            }else{
                pasengerAdapter.setFilteredData(searchResult)
            }
        }
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(apiInterface::class.java)
        val filter = HashMap<String, String>()
        filter["page"] = "0"
        filter["size"] = "5"
        val call = service.getData(filter)
        call.enqueue(object : Callback<PassengerData> {
            override fun onResponse(
                call: Call<PassengerData>,
                response: Response<PassengerData>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Toast.makeText(
                            applicationContext,
                            "Data fetch successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("API RESPONSE", response.body().toString())
                        val passengerData = response.body()

                        if (passengerData != null) {
                            dataItem = passengerData.data
                        }
                        binding.shimmerMainContent.visibility = View.GONE
                        binding.flightList.visibility = View.VISIBLE
                        pasengerAdapter = PasengerAdapter(dataItem, this@MainActivity)
                        binding.flightList.adapter = pasengerAdapter
                    }
                }
            }

            override fun onFailure(call: Call<PassengerData>, t: Throwable) {
                Log.e("error", "Api Failed", t)
                
            }
        })
    }

    companion object {
        var BaseUrl = "https://api.instantwebtools.net"
    }
}




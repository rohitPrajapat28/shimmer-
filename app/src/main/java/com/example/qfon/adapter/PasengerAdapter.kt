package com.example.qfon.adapter


import android.app.appsearch.SearchResult
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qfon.databinding.AdapterLayoutBinding
import com.example.qfon.models.AirlineItem
import com.example.qfon.models.DataItem

class PasengerAdapter(

    private var passengerData: List<DataItem>?,


    private var context: Context?
) : RecyclerView.Adapter<PasengerAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: AdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    fun setFilteredData( searchResult: List<DataItem>) {
        this.passengerData =searchResult
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(passengerData?.get(position) ?: passengerData){

                binding.idTv.text = passengerData?.get(position)?.id ?: "id"
                binding.nameTv.text = passengerData?.get(position)?.name ?: "name"
                binding.tripsTv.text = passengerData?.get(position)?.trips.toString()
                for (i in 0 until (passengerData?.get(position)!!.airline.size)) {
                   var airlineItem : AirlineItem = passengerData?.get(position)!!.airline.get(i)
                    binding.titleTv.text = airlineItem.name
                    binding.countryTv.text = airlineItem.country
                    binding.headQuaterTv.text =airlineItem.headQuaters
                    binding.sloganTv.text = airlineItem.slogan
                    binding.websiteTv.text = airlineItem.website
                    context?.let {
                        Glide.with(it)
                            .load(airlineItem.logo.toString())
                            .into(binding.logoImg)
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return passengerData?.size ?: 5
    }

}


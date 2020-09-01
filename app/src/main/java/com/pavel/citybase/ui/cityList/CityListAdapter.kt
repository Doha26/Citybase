package com.pavel.citybase.ui.cityList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavel.citybase.R
import com.pavel.citybase.databinding.ItemSearchResultBinding
import com.pavel.citybase.domain.city.City

class CityListAdapter(
    private val cities: List<City>,
    private val clickListener: (City) -> Unit
) :
    RecyclerView.Adapter<CityListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSearchResultBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_search_result, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(cities[position],clickListener)
    }


    class MyViewHolder(val binding: ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City,clickListener: (City) -> Unit) {
            binding.tvCityName.text = "${city.name}, ${city.country}"
            binding.tvCoordinates.text = "Lat:${city.coord.lat} , Lng : ${city.coord.lon}"
            binding.searchItemLayout.setOnClickListener {
                clickListener(city)
            }
        }
    }
}
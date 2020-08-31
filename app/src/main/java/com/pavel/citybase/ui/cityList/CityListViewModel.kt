package com.pavel.citybase.ui.cityList

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavel.citybase.domain.city.City
import com.pavel.citybase.domain.city.CityRepository
import com.pavel.citybase.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CityListViewModel(val cityRepository: CityRepository) : BaseViewModel(), Observable {
    private val initialCities: Collection<City> = cityRepository.cities;
    private val _cities: MutableLiveData<List<City>> = MutableLiveData()

    // Live data that would be observed to watch change on quesry change on the list
    val cities: LiveData<List<City>> = _cities;

    init {
        _cities.value = initialCities.toMutableList();
    }

    // this method take a query string as a parameter then perform search in the Main thread using Corotine
    fun performSearch(query: String) = viewModelScope.launch(Dispatchers.Main) {
        val searchResult: ArrayList<City> = cityRepository.search(query);
        Log.d("TOKI", searchResult.size.toString())
        // Set the result that match the query
        _cities.value = searchResult.toMutableList()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
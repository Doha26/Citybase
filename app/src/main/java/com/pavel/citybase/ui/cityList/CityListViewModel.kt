package com.pavel.citybase.ui.cityList

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavel.citybase.domain.city.City
import com.pavel.citybase.domain.city.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityListViewModel(val cityRepository: CityRepository) : ViewModel(), Observable {
    private val initialCities: Collection<City> = cityRepository.cities;
    private val _cities: MutableLiveData<List<City>> = MutableLiveData()
    val cities: LiveData<List<City>> = _cities;

    init {
        _cities.value = initialCities.toMutableList();
    }

    fun performSearch(query: String) = viewModelScope.launch(Dispatchers.Main) {
        val results: ArrayList<City> = cityRepository.search(query);
        _cities.value = results.toMutableList()
        Log.d("TOK",results.size.toString());
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
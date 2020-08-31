package com.pavel.citybase.ui.cityList

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.pavel.citybase.domain.city.City
import com.pavel.citybase.domain.city.CityRepository

class CityListViewModel(val cityRepository: CityRepository) : ViewModel(), Observable {
    private val initialCities: Collection<City> = cityRepository.cities;
    private val _cities: MutableLiveData<List<City>> = MutableLiveData()
    val cities: LiveData<List<City>> = _cities;
    init {
      _cities.value = initialCities.toMutableList();
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
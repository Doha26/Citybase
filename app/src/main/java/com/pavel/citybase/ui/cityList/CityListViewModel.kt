package com.pavel.citybase.ui.cityList

import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.pavel.citybase.domain.city.CityRepository

class CityListViewModel (val cityRepository : CityRepository) : ViewModel(), Observable {

    val cities = cityRepository.cities;

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
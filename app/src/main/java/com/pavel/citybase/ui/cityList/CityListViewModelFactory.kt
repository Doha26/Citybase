package com.pavel.citybase.ui.cityList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavel.citybase.domain.city.CityRepository
import java.lang.IllegalArgumentException

class CityListViewModelFactory (private val repository: CityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityListViewModel::class.java)){
            return CityListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view Model class¬")
    }

}
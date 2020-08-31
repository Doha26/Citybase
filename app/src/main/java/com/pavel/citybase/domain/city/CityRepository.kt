package com.pavel.citybase.domain.city

import android.content.Context

class CityRepository (private val daoImpl: CityDAOImpl){
    val cities = daoImpl.cities;
}
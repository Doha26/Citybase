package com.pavel.citybase.domain.city

import com.pavel.citybase.domain.city.CityDAO

class CityRepository (private val dao: CityDAO){
    val cities = dao.getAllCities();
}
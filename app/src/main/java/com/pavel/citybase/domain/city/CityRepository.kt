package com.pavel.citybase.domain.city

import android.content.Context
import android.util.Log
import com.pavel.citybase.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CityRepository(private val daoImpl: CityDAOImpl) {
    var cities: ArrayList<City> = ArrayList(daoImpl.cities);
    val sortedList: Collection<City> = cities.sortedWith(compareBy({ it.name }))

    fun search(query: String): ArrayList<City> {
        var resultCities: ArrayList<City> = ArrayList<City>()
        val resultSearchPosition = binarySearch(sortedList.toList(), query)
        if (resultSearchPosition == -1) {
            resultCities = ArrayList(cities.filter { it.name.startsWith(query, false) })
        } else {
            val city: City = cities.elementAt(resultSearchPosition);
            resultCities.clear()
            resultCities.add(city)
        }
        return resultCities
    }

    fun binarySearch(cityList: List<City>, searchValue: String): Int {
        var low = 0;
        var high = cityList.size - 1;
        var mid = (low + high) / 2;
        while (low <= high && !cityList.get(mid).equals(searchValue)) {

            if (cityList.get(mid).country.compareTo(searchValue) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;

            if (low > high) {
                mid = -1;
            }

        }
        return mid;
    }
}

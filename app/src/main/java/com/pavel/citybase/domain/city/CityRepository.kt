package com.pavel.citybase.domain.city

import android.util.Log

class CityRepository(private val daoImpl: CityDAOImpl) {
    var cities: ArrayList<City> = ArrayList(daoImpl.cities);

    // Sort the List for binary search purposes
    val sortedList: Collection<City> = cities.sortedWith(compareBy({ it.name }))

    fun search(query: String): ArrayList<City> {
        var resultCities: ArrayList<City> = ArrayList<City>()

        // Apply binary search to the initial list then observe it the index of the element that match the query is given
        val resultSearchPosition = binarySearch(sortedList.toList(), query)

        // If the binary search did'nt find the element ( - 1 returned) , then apply array filter to get entries that match the given query
        if (resultSearchPosition == -1) {
            resultCities = ArrayList(cities.filter {
                "${it.name}, ${it.country}".startsWith(
                    query,
                    false
                )
            }) // Search is case sensitive
        } else {
            //Log.d("NOT_MOIN_1",cities.elementAt(resultSearchPosition).toString())
            // if the binary search return the element, only return the element
            val city: City = cities.elementAt(resultSearchPosition);
            resultCities.clear()
            resultCities.add(city)
        }
        return resultCities
    }

    fun binarySearch(cityList: List<City>, searchValue: String): Int {
        var low = 0; // the min index of the list

        var high = cityList.size - 1;  // the Max index of the list

        var mid =
            (low + high) / 2;  // Divide the List in two parts to get the middle then move the search accordingly to if the element is on left on right

        while (low <= high && !cityList.get(mid).name.equals(searchValue)) {  // While the element is not found ? perform search by moving from low to middle
            val expression = "${cityList.get(mid).name} , ${cityList.get(mid).country}"
            if (expression.compareTo(searchValue) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;

            if (low > high) {  // if the element is not found at the half Left of the list and on the half right, then return -1
                mid = -1;
            }
        }
        return mid; // else the element is found, then return it's position in the list
    }
}

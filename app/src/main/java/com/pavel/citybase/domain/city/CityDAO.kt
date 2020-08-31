package com.pavel.citybase.domain.city
import android.content.Context
import com.google.gson.Gson
import com.pavel.citybase.R


interface CityDAO {
    val cities: Collection<City>
}

class CityDAOImpl(context: Context) : CityDAO {
    override val cities: Collection<City> by lazy {
        val assetsReader = context.resources.openRawResource(R.raw.cities).bufferedReader()
        Gson().newBuilder().create().fromJson(assetsReader, Array<City>::class.java).toList()
    }
}
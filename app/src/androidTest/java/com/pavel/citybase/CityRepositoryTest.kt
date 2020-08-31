package com.pavel.citybase

import androidx.test.InstrumentationRegistry
import com.pavel.citybase.domain.city.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CityRepositoryTest {
    val dao: CityDAO
    lateinit var daoImpl: CityDAOImpl
    lateinit var repository : CityRepository

    @Before
    fun setup() {
        daoImpl =  CityDAOImpl(InstrumentationRegistry.getTargetContext())
        repository = CityRepository(daoImpl)
    }


    init {
        dao = object : CityDAO {
            override val cities: Collection<City> = listOf(
                City(0, "Deunkerk", "", Coordinates(0.0, 0.0)),
                City(0, "Kief", "", Coordinates(0.0, 0.0)),
                City(0, "Alber", "", Coordinates(0.0, 0.0)),
                City(0, "Zoming", "", Coordinates(0.0, 0.0))
            )

        }
    }

    @Test
    fun test_query_with_empty_params_returns_all_locations() {
        runBlocking {
            repository.search("")
        }.also {
            assert(it.size == dao.cities.size)
        }
    }

    @Test
    fun test_query_by_city_name_filters_locations() {
        runBlocking {
            repository.search("Alberta")
        }.also {
            assert(it.size == 1)
            assert(it.all { location -> location.name == "Meda" })
        }
    }

    @Test
    fun test_query_by_city_name_filters_locations_to_empty_if_no_matches() {
        runBlocking {
            repository.search("domingoss")
        }.also {
            assert(it.isEmpty())
        }
    }

    @Test
    fun test_binary_search_return_minus_one_when_not_found() {
        runBlocking {
            repository.binarySearch(dao.cities.toList(), "domingos")
        }.also {
            assert(it == -1)
        }
    }

    @Test
    fun test_search_return_results_when_query_match() {
        runBlocking {
            repository.search("Alb")
        }.also {
            assert(it.size > 0)
        }
    }
}

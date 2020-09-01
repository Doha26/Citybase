package com.pavel.citybase

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.pavel.citybase.domain.city.CityDAO
import com.pavel.citybase.domain.city.CityDAOImpl
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CityDAOTest {
  private val dao: CityDAO

  init {
    dao = CityDAOImpl(InstrumentationRegistry.getTargetContext())
  }

  @Test
  fun test_data_source_is_loaded_and_converted() {
    assert(dao.cities.isEmpty().not())
  }

}

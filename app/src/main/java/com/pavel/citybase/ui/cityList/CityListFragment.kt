package com.pavel.citybase.ui.cityList

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavHost
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavel.citybase.ui.base.BaseFragment
import com.pavel.citybase.ui.base.factoryViewModel
import com.pavel.citybase.R
import com.pavel.citybase.databinding.CityListFragmentBinding
import com.pavel.citybase.domain.city.City
import com.pavel.citybase.domain.city.CityDAOImpl
import com.pavel.citybase.domain.city.CityRepository
import com.pavel.citybase.ui.cityDisplay.MapParams

class CityListFragment : BaseFragment<CityListViewModel>() {

    private lateinit var binding: CityListFragmentBinding

    override val cityListViewModel: CityListViewModel by factoryViewModel {
        val cityDAOImpl = CityDAOImpl(context!!)
        val repository = CityRepository(cityDAOImpl)
        CityListViewModel(repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.city_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.mViewModel = cityListViewModel
        binding.lifecycleOwner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        setupSearchView(view)
    }

    fun initRecyclerView(view: View) {
        binding.rvListCities.layoutManager = LinearLayoutManager(view.context)
        binding.rvListCities.apply {
            layoutManager = LinearLayoutManager(view.context)
            cityListViewModel.cities.observe(this@CityListFragment, Observer {
                if (it.size === 0) {
                    binding.tvNoResult.visibility = View.VISIBLE
                    binding.rvListCities.visibility = View.GONE
                } else {
                    binding.tvNoResult.visibility = View.GONE
                    binding.rvListCities.visibility = View.VISIBLE
                }
                binding.rvListCities.adapter =
                    CityListAdapter(it, { selectedIem: City -> displayOnMap(selectedIem) })
            })
        }

    }

    private fun setupSearchView(view: View) {
        binding.searchViewCity.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return this.onQueryTextSubmit(newText)
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { cityListViewModel.performSearch(it) }
                    return false
                }
            })
            setOnSearchClickListener { cityListViewModel.performSearch(query.toString()) }
            isIconified = false
        }
    }

    private fun displayOnMap(it: City) {
        (activity as NavHost).navController.navigate(
            CityListFragmentDirections.displayOnMap(
                MapParams(
                    it.name,
                    it.country,
                    it.coord.lat,
                    it.coord.lat
                )
            )
        )
    }


}
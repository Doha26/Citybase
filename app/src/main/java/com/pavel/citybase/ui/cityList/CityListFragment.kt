package com.pavel.citybase.ui.cityList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.pavel.citybase.R
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavHost
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavel.citybase.databinding.CityListFragmentBinding
import com.pavel.citybase.domain.city.City
import com.pavel.citybase.domain.city.CityDAO
import com.pavel.citybase.domain.city.CityRepository
import com.pavel.citybase.ui.cityDisplay.MapParams

 class CityListFragment : Fragment() {

    private lateinit var binding: CityListFragmentBinding
    private lateinit var cityListViewModel: CityListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.city_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cityDAO = CityDAO()
        val repository = CityRepository(cityDAO)
        val factory = CityListViewModelFactory(repository)
        cityListViewModel = ViewModelProvider(this, factory).get(CityListViewModel::class.java)
        binding.mViewModel = cityListViewModel
        binding.lifecycleOwner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        //setupSearchView(view)
    }

    fun initRecyclerView(view: View) {
        binding.rvListCities.layoutManager = LinearLayoutManager(view.context)
        binding.rvListCities.apply {
            layoutManager = LinearLayoutManager(view.context)
            if(this@CityListFragment::cityListViewModel.isInitialized){
                cityListViewModel.cities?.observe(this@CityListFragment, Observer {
                    binding.rvListCities.adapter =
                        CityListAdapter(it, { selectedIem: City -> displayOnMap(selectedIem) })
                })
            }
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
                   // viewModel.search(query)
                    return true
                }
            })
            //setOnSearchClickListener { viewModel.search(query.toString()) }
            isIconified = false
        }
    }

    private fun displayOnMap(it: City) {
        (activity as NavHost).navController.navigate(
            CityListFragmentDirections.displayOnMap(MapParams(it.name, it.coord.lat, it.coord.lat))
        )
    }

}
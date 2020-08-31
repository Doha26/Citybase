package com.pavel.citybase.ui.cityDisplay

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pavel.citybase.R
import com.pavel.citybase.databinding.FragmentCityDisplayBinding
import kotlinx.android.parcel.Parcelize

class CityDisplayFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCityDisplayBinding
    private val zoom = 12f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city_display, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_Map_mFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        binding.ivBack.setOnClickListener { view -> (activity as NavHost).navController.popBackStack() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val params = arguments?.let { CityDisplayFragmentArgs.fromBundle(it).params }
        binding.txtCityName.text = "${params?.city}, ${params?.country}"
        googleMap.apply {
            addMarker(
                params?.lat?.let { LatLng(it, params.lng) }?.let {
                    MarkerOptions()
                        .position(it)
                        .title(
                            this@CityDisplayFragment.getString(
                                R.string.map_marker_title,
                                params.city
                            )
                        )
                }
            )
            moveCamera(
                CameraUpdateFactory.newLatLngZoom(params?.lat?.let { LatLng(it, params.lng) }, zoom)
            )
        }
    }
}


@Parcelize
data class MapParams(val city: String, val country: String, val lat: Double, val lng: Double) :
    Parcelable
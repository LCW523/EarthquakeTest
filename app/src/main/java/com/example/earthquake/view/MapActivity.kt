package com.example.earthquake.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquake.R
import com.example.earthquake.bean.Earthquake
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val EXTRA_EARTHQUAKE = "extra_earthquake"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var earthquake: Earthquake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        earthquake = (intent.getSerializableExtra(EXTRA_EARTHQUAKE) ?: Earthquake(0.0, "", 0.0, 0.0)) as Earthquake
    }

    override fun onMapReady(googleMap: GoogleMap) { // Show coordinates on the map according to latitude and longitude
        mMap = googleMap
        val location = LatLng(earthquake.latitude, earthquake.longitude)
        mMap.addMarker(MarkerOptions().position(location).title("Earthquake Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 8f))
    }
}
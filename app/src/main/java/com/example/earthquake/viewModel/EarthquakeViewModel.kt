package com.example.earthquake.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquake.bean.Earthquake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */
class EarthquakeViewModel : ViewModel() {
    private val _earthquakes = MutableLiveData<List<Earthquake>>()  //use the LiveData to store seismic data
    val earthquakes: LiveData<List<Earthquake>> = _earthquakes

    fun fetchEarthquakes() {
        GlobalScope.launch(Dispatchers.IO) {
            val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2024-01-01&minmagnitude=7"
            val response = URL(url).readText() // Get JSON data from the API
            val earthquakes = parseEarthquakes(response)
            _earthquakes.postValue(earthquakes) // Update LiveData
        }
    }

    private fun parseEarthquakes(jsonString: String): List<Earthquake> { // Parse JSON data
        val earthquakes = mutableListOf<Earthquake>()
        val jsonObject = JSONObject(jsonString)
        val features = jsonObject.getJSONArray("features")
        for (i in 0 until features.length()) {
            val feature = features.getJSONObject(i)
            val properties = feature.getJSONObject("properties")
            val geometry = feature.getJSONObject("geometry")
            val coordinates = geometry.getJSONArray("coordinates")

            val magnitude = properties.getDouble("mag")
            val place = properties.getString("place")
            val latitude = coordinates.getDouble(1)
            val longitude = coordinates.getDouble(0)

            earthquakes.add(Earthquake(magnitude, place, latitude, longitude))
        }
        return earthquakes
    }
}
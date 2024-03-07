package com.example.earthquake.test
import com.example.earthquake.viewModel.EarthquakeViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */
class EarthquakeTest {

    @Test
    fun testFetchEarthquakes() {
        val viewModel = EarthquakeViewModel()
        runBlocking {
            viewModel.fetchEarthquakes() //Seismic data were obtained from the test
        }
        assertEquals(true, viewModel.earthquakes.value?.isNotEmpty())
    }

}
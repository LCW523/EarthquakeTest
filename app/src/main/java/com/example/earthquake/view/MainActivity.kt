package com.example.earthquake.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake.adapter.EarthquakeAdapter
import com.example.earthquake.adapter.EarthquakeClickListener
import com.example.earthquake.viewModel.EarthquakeViewModel
import com.example.earthquake.bean.Earthquake

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */

class MainActivity : AppCompatActivity(), EarthquakeClickListener {
    private val viewModel: EarthquakeViewModel by viewModels()
    private lateinit var adapter: EarthquakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = RecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        setContentView(recyclerView)

        adapter = EarthquakeAdapter(emptyList(), this)
        recyclerView.adapter = adapter

        viewModel.fetchEarthquakes() //Acquisition of seismic data
        viewModel.earthquakes.observe(this) { earthquakes ->
            adapter.updateData(earthquakes) //Display the seismic data on the recyclerView
        }
    }

    override fun onEarthquakeClicked(earthquake: Earthquake) { // Click the item event to pass the seismic data
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra(MapActivity.EXTRA_EARTHQUAKE, earthquake)
        startActivity(intent)
    }
}
package com.example.earthquake.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake.bean.Earthquake

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */
class EarthquakeAdapter(
    private var earthquakes: List<Earthquake>,
    private val clickListener: EarthquakeClickListener
) : RecyclerView.Adapter<EarthquakeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return EarthquakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.bind(earthquakes[position], clickListener)
    }

    override fun getItemCount(): Int = earthquakes.size

    fun updateData(newEarthquakes: List<Earthquake>) {
        earthquakes = newEarthquakes
        notifyDataSetChanged()
    }
}

class EarthquakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView = itemView.findViewById(android.R.id.text1)

    fun bind(earthquake: Earthquake, clickListener: EarthquakeClickListener) {
        val text = "Magnitude: ${earthquake.magnitude}\nPlace: ${earthquake.place}\nLatitude: ${earthquake.latitude}\nLongitude: ${earthquake.longitude}"
        textView.text = text
        if (earthquake.magnitude >= 7.5) { //A magnitude greater than or equal to 7.5 shows red
            textView.setTextColor(Color.RED)
        } else { //A magnitude less than 7.5 shows black
            textView.setTextColor(Color.BLACK)
        }
        itemView.setOnClickListener { //item click event monitor
            clickListener.onEarthquakeClicked(earthquake)
        }
    }
}

interface EarthquakeClickListener {
    fun onEarthquakeClicked(earthquake: Earthquake)
}
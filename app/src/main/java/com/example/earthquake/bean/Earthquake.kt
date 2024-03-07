package com.example.earthquake.bean

import java.io.Serializable

/**
 *@Author ChengWei Liu
 *@Date 07/03/2024
 */
data class Earthquake(
    val magnitude: Double,
    val place: String,
    val latitude: Double,
    val longitude: Double
) : Serializable
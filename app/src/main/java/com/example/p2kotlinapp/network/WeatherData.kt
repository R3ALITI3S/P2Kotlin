package com.example.p2kotlinapp.network

data class WeatherData(
    val rain: Double = 0.0,
    val cloudCover: Int = 0,
    val isDay: Boolean = false,
    val time:  Int = 0
)

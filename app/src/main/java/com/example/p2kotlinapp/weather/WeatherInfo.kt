package com.example.p2kotlinapp.weather

import com.example.p2kotlinapp.MainActivity

data class WeatherInfo {
    val weatherDataPerDay: Map<Int, List<MainActivity.WeatherData>>,
    val currentWeatherData: MainActivity.WeatherData?
}
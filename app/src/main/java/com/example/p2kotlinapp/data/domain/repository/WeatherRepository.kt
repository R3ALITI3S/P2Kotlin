package com.example.p2kotlinapp.data.domain.repository

import com.example.p2kotlinapp.data.domain.util.Resource
import com.example.p2kotlinapp.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
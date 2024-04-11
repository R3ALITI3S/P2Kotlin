package com.example.p2kotlinapp

import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

data class WeatherData(
    val cloudCover : Int = 0,
    val rainFall : Float = 0f,
    val isDay : Boolean = true
)

class WeatherGetter{
    val APILink = ("https://api.open-meteo.com/v1/forecast?latitude=57.048&longitude=9.9187&current=is_day,rain,cloud_cover&wind_speed_unit=ms")

    suspend fun getWeatherData()
    {
        val apiResponse = URL(APILink).readText()
        println(apiResponse)
    }
}


package com.example.p2kotlinapp

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
        @GET("weather")
        suspend fun getWeather(
            @Query("q") city: String,
            @Query("apiKey") apiKey: String
        ): MainActivity.WeatherData
}

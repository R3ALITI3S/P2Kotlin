package com.example.p2kotlinapp

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("/v1/forecast?current=temperature_2m&hourly=temperature_2m&timezone=Europe%2FBerlin&")
    suspend fun getWeather(
        @Query("latitude") la: Double,
        @Query("longitude") long: Double,
        ): MainActivity.WeatherData
}

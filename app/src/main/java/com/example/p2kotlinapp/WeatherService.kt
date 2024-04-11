package com.example.p2kotlinapp

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("vl/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeather(
        @Query("latitude") la: Double,
        @Query("longitude") long: Double,

        ): MainActivity.WeatherData

}

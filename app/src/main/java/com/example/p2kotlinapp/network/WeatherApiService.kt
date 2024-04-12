package com.example.p2kotlinapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.open-meteo.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("/v1/forecast?latitude=57.0488&longitude=9.9217&current=is_day,rain,cloud_cover&wind_speed_unit=ms&timezone=Europe%2FBerlin")
    suspend fun getCurrentWeather(): String
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
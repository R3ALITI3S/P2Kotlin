package com.example.p2kotlinapp

import retrofit2.Response
import retrofit2.http.GET

data class WeatherData(
    val cloudCover : Int = 0,
    val rainFall : Float = 0f,
    val isDay : Boolean = true
)


interface TodoApi {
    @GET("/v1/forecast?latitude=57.048&longitude=9.9187&current=is_day,rain,cloud_cover&wind_speed_unit=ms")
    suspend fun getTodos(): Response<WeatherData>
}
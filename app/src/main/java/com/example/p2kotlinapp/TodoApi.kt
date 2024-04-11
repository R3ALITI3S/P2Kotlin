package com.example.p2kotlinapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    @GET("/v1/forecast?latitude=57.048&longitude=9.9187&current=is_day,rain,cloud_cover&wind_speed_unit=ms")
    suspend fun getTodos(): Response<WeatherData>
}
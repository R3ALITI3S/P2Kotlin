package com.example.p2kotlinapp.network.WeatherApiService

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://api.open-meteo.com"


// Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// Retrofit service object for creating api calls
interface WeatherApiService {
    @GET("photos")
    suspend fun getPhotos(): List<WeatherPhoto>
}
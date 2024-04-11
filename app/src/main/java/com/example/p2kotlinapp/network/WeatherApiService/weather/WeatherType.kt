package com.example.p2kotlinapp.network.WeatherApiService.weather

import androidx.annotation.DrawableRes

sealed class WeatherType (
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
){
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = // R.drawable
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
            }
        }
    }
}
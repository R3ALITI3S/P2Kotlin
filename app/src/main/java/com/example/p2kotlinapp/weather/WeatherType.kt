package com.example.p2kotlinapp.weather

import androidx.annotation.DrawableRes

sealed class WeatherType (
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType (
        weatherDesc = "Clear sky",
        iconRes = // R.drawable
    )

    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = // R.drawable.ic_rainy
    )

    companion object {
        fun fromWMO(code:Int) : WeatherType {
            return when(code) {
                0 -> ClearSky
                63 -> ModerateRain
                else -> ClearSky
            }
        }
    }
}
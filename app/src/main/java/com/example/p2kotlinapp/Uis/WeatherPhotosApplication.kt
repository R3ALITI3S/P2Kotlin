package com.example.p2kotlinapp.Uis

import android.app.Application
import com.example.weatherphotos.data.AppContainer
import com.example.weatherphotos.data.DefaultAppContainer


class WeatherPhotosApplication {

    class P2KotlinApp : Application() {
        lateinit var container: AppContainer
        override fun onCreate() {
            super.onCreate()
            container = DefaultAppContainer()
        }
    }
}
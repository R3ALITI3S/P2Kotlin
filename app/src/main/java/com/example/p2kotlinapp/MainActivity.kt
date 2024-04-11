package com.example.p2kotlinapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {

    data class WeatherData(
        val name: String,
        val press: Main,
        val description: Main,
        val main: Main, // temp
        val second: Second,
        val windSpeed: Main,
        val rain: Main,
        val weather: List<Weather>

    )

    data class Main(
        val temperature_2m: Double,
        val summary: String,
        val pressure: Int,
        val windSpeed: Double,
        val rain: Double
    )



    data class Second(
        val uvi: Double
    )

    data class Weather(
        val icon: String
    )

    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        provideApi()

        // Replace "CityName" with the desired city
        GlobalScope.launch(Dispatchers.IO) {
            val weatherData = weatherService.getWeather(57.046263, 9.921526)
            withContext(Dispatchers.Main) {
                updateUI(weatherData)

                println("stuff is working right?")
            }
        }
    }

    private fun provideApi() : WeatherService {

        println("Provides the Api")

        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    private fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text = weatherData.name
        findViewById<TextView>(R.id.textViewDes).text = weatherData.main.summary
      //  findViewById<TextView>(R.id.textViewTemperature).text =  "${weatherData.main.temp.toInt()-273}°C"
        findViewById<TextView>(R.id.textViewUVI).text = "${weatherData.main.pressure}"
        findViewById<TextView>(R.id.textViewWind).text = "${weatherData.main.windSpeed}"
        findViewById<TextView>(R.id.textViewRain).text = "${weatherData.main.rain}"

       // val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
    }
}





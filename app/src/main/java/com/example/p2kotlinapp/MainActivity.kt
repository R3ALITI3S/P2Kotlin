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
        val weather: List<Weather>
    )

    data class Main(
        val temp: Double,
        val summary: String,
        val pressure: Int,
        val windSpeed: Double
    )



    data class Second(
        val uvi: Double
    )

    data class Weather(
        val icon: String
    )

    private val apiKey = "e99786d5749a804fa900b44629711d41"
    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)

        // Replace "CityName" with the desired city
        GlobalScope.launch(Dispatchers.IO) {
            val weatherData = weatherService.getWeather("Aalborg", apiKey)
            withContext(Dispatchers.Main) {
                updateUI(weatherData)
            }
        }
    }

    private fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text = weatherData.name
        findViewById<TextView>(R.id.textViewDes).text = weatherData.main.summary
        findViewById<TextView>(R.id.textViewTemperature).text =
            "${weatherData.main.temp.toInt()-273}°C"
        findViewById<TextView>(R.id.textViewUVI).text = "${weatherData.main.pressure}"
        findViewById<TextView>(R.id.textViewWind).text = "${weatherData.main.windSpeed}"

        val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
    }
}

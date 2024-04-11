package com.example.p2kotlinapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    // everything below is a test
    data class WeatherData(
        val name: String,
        val press: Main,
        val description: Main,
        val main: Main, // temp
        val second: Second,
        val windSpeed: Main,
        val rain: Main,
        val temperature: Main,
        val weather: List<Weather>

    )


//    data class WeatherDataDao(
//        val time: List<String>,
//        @field:Json(name = "temperature_2m")
//        val temperatures: List<Double>,
//        @field:Json(name = "weathercode")
//        val weatherCodes: List<Int>,
//        @field:Json(name = "pressure_msl")
//        val pressures: List<Double>,
//        @field:Json(name = "windspeed_10m")
//        val windSpeeds: List<Double>
//    )

    data class Main(
        val temperature_2m: Double,
        val summary: String,
        val pressure: Int,
        val windSpeed: Double,
        val rain: Double
    )

//    data class Main(
//        val time: List<String>,
//        @field:Json(name = "temperature_2m")
//        val temperatures: List<Double>,
//        @field:Json(name = "weathercode")
//        val weatherCodes: List<Int>,
//        @field:Json(name = "pressure_msl")
//        val pressures: List<Double>,
//        @field:Json(name = "windspeed_10m")
//        val windSpeeds: List<Double>
//    )


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

        // I tried to put the coordinates into the function..
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

    // REMEMBER TO UPDATE THE UI
    private fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text = weatherData.name
        findViewById<TextView>(R.id.textViewDes).text = weatherData.main.summary

        // findViewById<TextView>(R.id.textViewTemperature).text = "${weatherData.main.temp}"

        findViewById<TextView>(R.id.textViewTemperature).text =  "${weatherData.main.temperature_2m.toInt()-273}°C"
        findViewById<TextView>(R.id.textViewUVI).text = "${weatherData.main.pressure}"
        findViewById<TextView>(R.id.textViewWind).text = "${weatherData.main.windSpeed}"
        findViewById<TextView>(R.id.textViewRain).text = "${weatherData.main.rain}"

       // val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
    }
}





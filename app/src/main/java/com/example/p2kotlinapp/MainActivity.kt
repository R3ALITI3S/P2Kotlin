package com.example.p2kotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : ComponentActivity() {

    // Step 5: Pass the JSON response
    data class WeatherResponse(
        val name: String,
        val main: Main,
        val weather: List<Weather>

    )

    data class Main(
        val temp: Double,
        val feelsLike: Double,
        val tempMin: Double,
        val tempMax: Double,
        val pressure: Int,
        val humidity: Int
    )

    data class Weather(
        val description: String,
        val icon: String
    )

    interface WeatherService {
        @GET("Weather")
        suspend fun getWeather(
            @Query("q") city: String,
            @Query("appid") apiKey: String
        ): WeatherResponse
    }
    // CODE SHIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_layout)

        // Define your Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Define your WeatherService interface
        val service = retrofit.create(WeatherService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getWeather("New York", "your_api_key_here")
                // Update UI with weather data (on the main thread)
                withContext(Dispatchers.Main) {
                    // Handle the weather response
                    handleWeatherResponse(response)
                }
            } catch (e: HttpException) {
                // Handle network errors
            } catch (e: Exception) {
                // Handle other exceptions
            }
        }
    }

    private fun handleWeatherResponse(weatherResponse: WeatherResponse) {
        val cityName = weatherResponse.name
        val temperature = weatherResponse.main.temp
    }
}





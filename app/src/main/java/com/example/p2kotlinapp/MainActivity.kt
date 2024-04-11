package com.example.p2kotlinapp

import android.content.ContentValues.TAG
import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.p2kotlinapp.ui.theme.P2KotlinAppTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import retrofit2.Response


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    // Den starter her :)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P2KotlinAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    setContentView(R.layout.activity_main)
                }

                lifecycleScope.launchWhenCreated {
                    val response = try {
                        Retrofitinstance.api.getTodos()
                        val response: Response<WeatherData> = Retrofitinstance.api.getTodos()
                        UpdateUI()
                    } catch (e: IOException) {
                        Log.e(TAG, "IOException: ${e.message}", e)
                    } catch (e: HttpException) {
                        Log.e(TAG, "HttpException: ${e.message}", e)
                        return@launchWhenCreated
                    }
                   // if (response.isSuccessful && response.body() != null) {

                //} else {
                        Log.e(TAG, "Response not successful")
                //    }

                }
            }
        }

//        // Replace "CityName" with the desired city
//        GlobalScope.launch(Dispatchers.IO) {
//            val weatherData = weatherService.getWeather("Aalborg", apiKey)
//            withContext(Dispatchers.Main) {
//                updateUI(weatherData)
//            }
//        }
    }

    fun getWeatherData() = runBlocking {
        val weatherGetter : WeatherGetter = WeatherGetter()
        launch { weatherGetter.getWeatherData() }


    }

    private fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text = weatherData.name
        findViewById<TextView>(R.id.textViewDes).text = weatherData.main.summary
        findViewById<TextView>(R.id.textViewTemperature).text =
            "${weatherData.main.temp.toInt()-273}°C"
        findViewById<TextView>(R.id.textViewUVI).text = "${weatherData.main.pressure}"
        findViewById<TextView>(R.id.textViewWind).text = "${weatherData.main.windSpeed}"

        // val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
    }
}

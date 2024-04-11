package com.example.p2kotlinapp

import android.content.ContentValues.TAG
import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
                        updateUI(WeatherData())
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
    }


    fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text // = weatherData
        findViewById<TextView>(R.id.textViewDes).text = "${weatherData.isDay}"// = weatherData // summary
        findViewById<TextView>(R.id.textViewTemperature).text  // = "${weatherData.main.temp.toInt()-273}°C"
        findViewById<TextView>(R.id.textViewUVI).text = "${weatherData.rainFall}"// = "${weatherData.main.pressure}"
        findViewById<TextView>(R.id.textViewWind).text = "${weatherData.cloudCover}"

        // val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
    }
}

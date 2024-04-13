package com.example.p2kotlinapp


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p2kotlinapp.network.WeatherApi
import com.example.p2kotlinapp.network.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeatherScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(WeatherScreenState())
    val state: StateFlow<WeatherScreenState> = _state.asStateFlow()

    var weatherData: WeatherData = WeatherData()
    init {
        Log.i("ViewModel","Initializing Weather Screen ViewModel")
        getCurrentWeatherData()
    }

    fun getCurrentWeatherData() {
        viewModelScope.launch {
            var success: Boolean = false
            val listResult: String = try {
                success = true
                WeatherApi.retrofitService.getCurrentWeather()
            }
            catch (e: Exception) {
                Log.e("EXCEPTION", "$e.message")
                "ERROR: $e.message"
            }

            _state.update{it.copy(
                fullResponse = listResult
            ) }

            if(success) {
                val jsonObject: JSONObject = JSONObject(listResult)
                val current = jsonObject.getJSONObject("current")

                val _weatherData = WeatherData(
                    rain = current.getDouble("rain"),
                    cloudCover = current.getInt("cloud_cover"),
                    isDay = when(current.getInt("is_day")) {
                        1 -> true
                        else -> false
                    }
                )

                //saves the extracted weather data to weatherData variable
                weatherData = _weatherData

                _state.update{it.copy(
                    rain = _weatherData.rain.toString(),
                    cloudy = _weatherData.cloudCover.toString(),
                    isDay = _weatherData.isDay.toString(),
                    fullResponse = current.toString()
                ) }
            }
        }
    }
}
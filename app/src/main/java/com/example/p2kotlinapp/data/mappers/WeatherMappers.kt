package com.example.p2kotlinapp.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.p2kotlinapp.data.remote.WeatherDataDto
import com.example.p2kotlinapp.weather.WeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]

        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]

        val humidity = humidities[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelcius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            // weatherType = WeatherType.fromWMO(weatherCode)

        )
    }.groupBy {
        it.time.dayOfMonth
        // it maps the day of the month to the list of weather data.
    }
}
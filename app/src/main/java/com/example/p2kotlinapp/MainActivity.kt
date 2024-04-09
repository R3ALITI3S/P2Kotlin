import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.p2kotlinapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : ComponentActivity() {

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
        @GET("weather")
        suspend fun getWeather(
            @Query("q") city: String,
            @Query("appid") apiKey: String
        ): WeatherResponse
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_layout)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openweathermap.org/weathermap")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)

        lifecycleScope.launch {
            try {
                val response = service.getWeather("New York", "e99786d5749a804fa900b44629711d41")
                withContext(Dispatchers.Main) {
                    handleWeatherResponse(response)
                }
            } catch (e: HttpException) {
                println("Network error: ${e.message()}")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun handleWeatherResponse(weatherResponse: WeatherResponse) {
        val cityName = weatherResponse.name
        val temperature = weatherResponse.main.temp
        println("City: $cityName, Temperature: $temperature")
    }
}

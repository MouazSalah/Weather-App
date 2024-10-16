package com.vodafone.weather.modules.listing.data.api

import com.vodafone.weather.modules.listing.data.models.DailyWeatherResponse
import retrofit2.Response
import retrofit2.http.*

interface WeatherApiService {

    companion object {
        private const val GET_DAILY_WEATHER = "onecall"
    }

    @GET(GET_DAILY_WEATHER)
    fun getDailyWeather(
        @QueryMap params: HashMap<String, String?>
    ):Response<DailyWeatherResponse>
}
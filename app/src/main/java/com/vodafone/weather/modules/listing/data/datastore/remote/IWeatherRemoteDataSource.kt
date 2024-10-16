package com.vodafone.weather.modules.listing.data.datastore.remote

import com.vodafone.weather.modules.listing.data.models.DailyWeatherResponse
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import retrofit2.Response

interface IWeatherRemoteDataSource {
    suspend fun getDailyWeather(params : DailyWeatherParams) : Response<DailyWeatherResponse>
}
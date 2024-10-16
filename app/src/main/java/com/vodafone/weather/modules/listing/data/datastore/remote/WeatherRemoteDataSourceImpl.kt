package com.vodafone.weather.modules.listing.data.datastore.remote

import com.vodafone.weather.extesnion.toHashMapParams
import com.vodafone.weather.modules.listing.data.api.WeatherApiService
import com.vodafone.weather.modules.listing.data.models.DailyWeatherResponse
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(private val apiInterface : WeatherApiService) :
    IWeatherRemoteDataSource {


    override suspend fun getDailyWeather(params: DailyWeatherParams): Response<DailyWeatherResponse> {
        return apiInterface.getDailyWeather(params  = params.toHashMapParams()!! )
    }
}
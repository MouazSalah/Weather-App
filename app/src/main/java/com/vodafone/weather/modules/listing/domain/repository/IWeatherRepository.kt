package com.vodafone.weather.modules.listing.domain.repository

import com.vodafone.weather.db.api.ApiResult
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity

interface IWeatherRepository {

    suspend fun getDailyWeather(params: DailyWeatherParams): ApiResult<List<DailyWeatherEntity>>
}

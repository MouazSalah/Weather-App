package com.vodafone.weather.modules.listing.domain.usecases

import com.vodafone.weather.db.api.ApiResult
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity
import com.vodafone.weather.modules.listing.domain.repository.IWeatherRepository
import javax.inject.Inject

class DailyWeatherUseCase @Inject constructor(private val repository: IWeatherRepository) {
    suspend operator fun invoke(params: DailyWeatherParams): ApiResult<List<DailyWeatherEntity>> {
        return repository.getDailyWeather(params)
    }
}
package com.vodafone.weather.modules.listing.data.datastore.local

import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity

interface IWeatherLocalDataSource {
    suspend fun loadDailyWeather(): List<DailyWeatherEntity>?

    suspend fun insertDailyWeather(movies: List<DailyWeatherEntity>)
}
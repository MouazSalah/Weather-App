package com.vodafone.weather.modules.listing.data.datastore.local

import com.vodafone.weather.db.room.WeatherDao
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(private val weatherDao: WeatherDao) :
    IWeatherLocalDataSource {

    override suspend fun loadDailyWeather(): List<DailyWeatherEntity>? = weatherDao.getDailyWeather()

    override suspend fun insertDailyWeather(movies: List<DailyWeatherEntity>) = weatherDao.insertDailyWeather(movies)
}
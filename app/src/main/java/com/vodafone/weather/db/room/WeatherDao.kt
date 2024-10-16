package com.vodafone.weather.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(movies: List<DailyWeatherEntity>)

    @Query("SELECT * FROM daily_weather")
    suspend fun getDailyWeather(): List<DailyWeatherEntity>?

    @Query("DELETE FROM daily_weather")
    suspend fun clearAll()
}
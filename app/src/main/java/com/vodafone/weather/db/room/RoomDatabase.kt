package com.vodafone.weather.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity
import com.vodafone.weather.modules.listing.domain.entites.WeatherConverter

@Database(entities = [DailyWeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeatherConverter::class)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun dailyWeatherDao(): WeatherDao
}
package com.vodafone.weather.modules.listing.domain.entites

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherConverter {

    @TypeConverter
    fun fromDailyWeatherList(movies: List<DailyWeatherEntity>): String {
        return Gson().toJson(movies)
    }

    @TypeConverter
    fun toDailyWeatherList(moviesJson: String): List<DailyWeatherEntity> {
        val type = object : TypeToken<List<DailyWeatherEntity>>() {}.type
        return Gson().fromJson(moviesJson, type)
    }
}
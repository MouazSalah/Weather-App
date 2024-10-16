package com.vodafone.weather.modules.listing.domain.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "daily_weather")
@TypeConverters(WeatherConverter::class)
data class DailyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ?= null,
    val dt: Long ?= null,

    val day: Float ?= null,
    val min: Float ?= null,
    val max: Float ?= null,

    val description: String ?= null,
    val icon: String ?= null,
)

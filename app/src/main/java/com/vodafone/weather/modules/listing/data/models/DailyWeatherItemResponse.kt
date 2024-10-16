package com.vodafone.weather.modules.listing.data.models

data class DailyWeatherItemResponse(
    val id: Int ?= null,
    val dt: Long ?= null,

    val day: Float ?= null,
    val min: Float ?= null,
    val max: Float ?= null,

    val description: String ?= null,
    val icon: String ?= null
)
package com.vodafone.weather.modules.listing.data.models

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @field:SerializedName("weather")
	val dailyWeather: List<DailyWeatherItemResponse?>? = null
)
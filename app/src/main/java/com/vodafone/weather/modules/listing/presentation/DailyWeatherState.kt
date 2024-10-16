package com.vodafone.weather.modules.listing.presentation

import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity

sealed class DailyWeatherState {
    data class Shimmer(var isShow: Boolean) : DailyWeatherState()
    data class Loading(var isShow: Boolean) : DailyWeatherState()
    data class Success(val dailyWeather: List<DailyWeatherEntity>) : DailyWeatherState()

    data class ApiError(val date: String) : DailyWeatherState()
    data object InternetError : DailyWeatherState()
}
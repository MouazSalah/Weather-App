package com.vodafone.weather.modules.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import com.vodafone.weather.modules.listing.domain.usecases.DailyWeatherUseCase
import com.vodafone.weather.db.api.ApiResult
import com.vodafone.weather.db.datastore.DataStoreManager
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyWeatherViewModel @Inject constructor(
    private val dailyWeatherUseCase: DailyWeatherUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel()
{
    private val _dailyWeatherState = MutableStateFlow<DailyWeatherState>(
        DailyWeatherState.Loading(
            false
        )
    )
    val dailyWeatherState: StateFlow<DailyWeatherState> = _dailyWeatherState

    private var dailyWeatherParams : DailyWeatherParams = DailyWeatherParams(
        lat = 25.276987, // Example latitude for Dubai
        lon = 55.296249, // Example longitude for Dubai
        exclude = "current,minutely,hourly,alerts",
        units = "metric",
    )

    private var dailyWeathers: List<DailyWeatherEntity>? = null

    init {
        fetchDailyWeather()
    }

     fun fetchDailyWeather() {

         viewModelScope.launch {
             _dailyWeatherState.value = DailyWeatherState.Shimmer(true)
             delay(2000) // to show shimmer

             _dailyWeatherState.value = DailyWeatherState.Shimmer(false)

            when (val moviesResult = dailyWeatherUseCase(dailyWeatherParams)) {
                is ApiResult.Success -> {
                    dailyWeathers = moviesResult.data
                    _dailyWeatherState.value =
                        DailyWeatherState.Success(dailyWeather = dailyWeathers ?: emptyList())
                }
                is ApiResult.ApiError -> {
                    _dailyWeatherState.value = DailyWeatherState.ApiError("")
                }
                is ApiResult.InternetError -> {
                    _dailyWeatherState.value = DailyWeatherState.InternetError
                }
            }
         }
    }

    private fun getWishListIds() : List<String> {
        return dataStoreManager.getWishlistList()
    }
}
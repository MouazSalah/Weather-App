package com.vodafone.weather.modules.listing.domain.repository

import com.vodafone.weather.extesnion.isNetworkAvailable
import com.vodafone.weather.core.BaseApp
import com.vodafone.weather.db.api.ApiResult
import com.vodafone.weather.modules.listing.data.datastore.local.IWeatherLocalDataSource
import com.vodafone.weather.modules.listing.data.datastore.remote.IWeatherRemoteDataSource
import com.vodafone.weather.modules.listing.data.params.DailyWeatherParams
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity
import com.vodafone.weather.modules.listing.domain.mapper.DailyWeatherMapper
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: IWeatherLocalDataSource,
    private val remoteDataSource: IWeatherRemoteDataSource
) : IWeatherRepository {


    private suspend fun insertDailyWeatherToLocalStorage(dailyWeathers: List<DailyWeatherEntity>) {
        localDataSource.insertDailyWeather(dailyWeathers)
    }

    private suspend fun getDailyWeatherFromLocalStorage(errorMessage: String): ApiResult<List<DailyWeatherEntity>> {
        val dailyWeathers : List<DailyWeatherEntity> = localDataSource.loadDailyWeather() ?: emptyList()
        return if (dailyWeathers.isNotEmpty()) {
            ApiResult.Success(dailyWeathers)
        } else {
            ApiResult.InternetError(errorMessage)
        }
    }

    override suspend fun getDailyWeather(params: DailyWeatherParams): ApiResult<List<DailyWeatherEntity>> {
        return if (isNetworkAvailable(BaseApp.instance.applicationContext)) {
            try {
                val response = remoteDataSource.getDailyWeather(params)

                if (response.isSuccessful) {
                    response.body()?.let { dailyWeatherResponse ->
                        val dailyWeatherEntities : List<DailyWeatherEntity> = DailyWeatherMapper.mapWeatherResponseToEntities(dailyWeatherResponse)
                        insertDailyWeatherToLocalStorage(dailyWeatherEntities)
                        ApiResult.Success(dailyWeatherEntities)
                    } ?: run {
                        getDailyWeatherFromLocalStorage(errorMessage = "${response.code()} - ${response.message()}")
                    }
                } else {
                    getDailyWeatherFromLocalStorage(errorMessage = "${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                getDailyWeatherFromLocalStorage(errorMessage = e.message.toString())
            }
        } else {
            getDailyWeatherFromLocalStorage(errorMessage = "No internet connection")
        }
    }
}
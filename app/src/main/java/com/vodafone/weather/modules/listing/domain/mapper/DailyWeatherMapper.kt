package com.vodafone.weather.modules.listing.domain.mapper

import com.vodafone.weather.modules.listing.data.models.DailyWeatherItemResponse
import com.vodafone.weather.modules.listing.data.models.DailyWeatherResponse
import com.vodafone.weather.modules.listing.domain.entites.DailyWeatherEntity

object DailyWeatherMapper {

    fun mapWeatherResponseToEntities(response : DailyWeatherResponse) : List<DailyWeatherEntity> {

        val items : ArrayList<DailyWeatherEntity> =
            mapWeatherToEntities(response.dailyWeather)

        return items
    }

    private fun mapWeatherToEntities(items: List<DailyWeatherItemResponse?>?) : ArrayList<DailyWeatherEntity> {
        val dailyWeather : ArrayList<DailyWeatherEntity> = arrayListOf()

        items?.forEach { model ->
            val item = DailyWeatherEntity(
                id = model?.id ?: 0
            )

            dailyWeather.add(item);
        }

        return dailyWeather
    }
}
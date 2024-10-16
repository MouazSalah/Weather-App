package com.vodafone.weather.modules.listing.data.params

import com.google.gson.annotations.SerializedName
import com.vodafone.weather.core.HashMapParams

data class DailyWeatherParams(
    @SerializedName("lat")
    var lat: Double ?= null,

    @SerializedName("lon")
    var lon: Double ?= null,

    @SerializedName("exclude")
    var exclude: String ?= null,

    @SerializedName("units")
    var units: String ?= null,

    @SerializedName("appid")
    var appid: String ?= null

) : HashMapParams {
    override fun dataClass(): Any = this
}
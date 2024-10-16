package com.vodafone.weather.db.api

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class ApiError(val exception: String) : ApiResult<Nothing>()
    data class InternetError(val exception: String) : ApiResult<Nothing>()
}
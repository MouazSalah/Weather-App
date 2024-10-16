package com.vodafone.weather.db.api

import com.vodafone.weather.core.Constants.API_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request : Request.Builder =  buildAPIRequest(chain)

        return chain.proceed(request.build())
    }
}

fun buildAPIRequest(chain: Interceptor.Chain): Request.Builder {
    return chain.request().newBuilder()
        .header("Authorization", "Bearer ${API_ACCESS_TOKEN}")
        .header("accept", "application/json")
}
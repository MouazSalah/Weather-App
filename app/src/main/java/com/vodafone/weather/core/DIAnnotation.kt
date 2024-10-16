package com.vodafone.weather.core

import javax.inject.Qualifier

object DIAnnotation {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class WeatherRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class WeatherInterceptor
}
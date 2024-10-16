package com.vodafone.weather.di

import com.vodafone.weather.core.DIAnnotation
import com.vodafone.weather.db.room.WeatherDao
import com.vodafone.weather.modules.listing.data.api.WeatherApiService
import com.vodafone.weather.modules.listing.data.datastore.local.IWeatherLocalDataSource
import com.vodafone.weather.modules.listing.data.datastore.local.WeatherLocalDataSourceImpl
import com.vodafone.weather.modules.listing.data.datastore.remote.IWeatherRemoteDataSource
import com.vodafone.weather.modules.listing.data.datastore.remote.WeatherRemoteDataSourceImpl
import com.vodafone.weather.modules.listing.domain.repository.IWeatherRepository
import com.vodafone.weather.modules.listing.domain.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DailyWeatherModule {

    @Singleton
    @Provides
    fun provideWeatherApiService(@DIAnnotation.WeatherRetrofit retrofit: Retrofit): WeatherApiService = retrofit.create(
        WeatherApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: WeatherApiService): IWeatherRemoteDataSource = WeatherRemoteDataSourceImpl(apiInterface)

    @Singleton
    @Provides
    fun provideLocalDataSource(weatherDao: WeatherDao): IWeatherLocalDataSource = WeatherLocalDataSourceImpl(weatherDao)


    @Singleton
    @Provides
    fun provideWeatherRepository(localDataSource: WeatherLocalDataSourceImpl,
                                remoteDataSource: WeatherRemoteDataSourceImpl
    ) :
            IWeatherRepository = WeatherRepositoryImpl(localDataSource, remoteDataSource)
}
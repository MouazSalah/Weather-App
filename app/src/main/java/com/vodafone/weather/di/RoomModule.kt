package com.vodafone.weather.di

import android.content.Context
import androidx.room.Room
import com.vodafone.weather.db.room.WeatherDao
import com.vodafone.weather.db.room.WeatherRoomDatabase
import com.vodafone.weather.modules.listing.data.datastore.local.IWeatherLocalDataSource
import com.vodafone.weather.modules.listing.data.datastore.local.WeatherLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    companion object {
        private const val DATA_BASE_NAME = "IMDB_daily_weather_database"
    }

    @Singleton
    @Provides
    fun provideWeatherDao(dataBase: WeatherRoomDatabase): WeatherDao = dataBase.dailyWeatherDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dailyWeatherDao : WeatherDao): IWeatherLocalDataSource = WeatherLocalDataSourceImpl(dailyWeatherDao)


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherRoomDatabase::class.java,
            DATA_BASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}
package com.vodafone.weather.di

import android.content.Context
import com.google.gson.Gson
import com.vodafone.weather.db.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()


    @Singleton
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context, gson: Gson) = DataStoreManager(context, gson)
}
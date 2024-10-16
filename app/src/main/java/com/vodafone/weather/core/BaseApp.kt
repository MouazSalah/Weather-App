package com.vodafone.weather.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApp : Application() {

    companion object {
        lateinit var instance: BaseApp private set
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return super.createStackElementTag(element) + " line: " + element.lineNumber
            }
        })
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initTimber()
    }
}
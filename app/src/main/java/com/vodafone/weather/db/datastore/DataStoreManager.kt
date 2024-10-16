package com.vodafone.weather.db.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(private val context: Context, val gson: Gson) {

    companion object {
        val MOVIES_WISHLIST_IDS = stringPreferencesKey("MOVIES_WISHLIST_IDS")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MOVIES_PREF")

    private fun readPrefStringBlocking(
        key: Preferences.Key<String>,
        defaultValue: String? = null
    ): String? = runBlocking { context.dataStore.data.first() }[key] ?: defaultValue


    private fun writePrefString(
        key: Preferences.Key<String>,
        value: String?,
        coroutineScope: CoroutineScope,
        callBack: (Boolean) -> Unit = {}
    ) {
        coroutineScope.launch {
            context.dataStore.edit { settings ->
                if (value != null) {
                    settings[key] = value
                    callBack.invoke(true)
                } else
                    callBack.invoke(false)
            }
        }
    }

    private fun saveWishlistList(
        list: List<String>,
        coroutineScope: CoroutineScope,
        callBack: (Boolean) -> Unit = {}
    ) {
        val jsonString = gson.toJson(list)
        writePrefString(MOVIES_WISHLIST_IDS, jsonString, coroutineScope, callBack)
    }

    fun addMovieToWishlist(movieId: String, coroutineScope: CoroutineScope, callBack: (Boolean) -> Unit = {}) {
        val wishlist = getWishlistList().toMutableList()
        if (!wishlist.contains(movieId)) {
            wishlist.add(movieId)
            saveWishlistList(wishlist, coroutineScope, callBack)
        } else {
            callBack.invoke(false)
        }
    }

    fun removeMovieFromWishlist(movieId: String, coroutineScope: CoroutineScope, callBack: (Boolean) -> Unit = {}) {
        val wishlist = getWishlistList().toMutableList()
        if (wishlist.contains(movieId)) {
            wishlist.remove(movieId)
            saveWishlistList(wishlist, coroutineScope, callBack)
        } else {
            callBack.invoke(false)
        }
    }

    fun getWishlistList(): List<String> {
        val jsonString = readPrefStringBlocking(MOVIES_WISHLIST_IDS)
        return if (jsonString != null) {
            try {
                gson.fromJson(jsonString, Array<String>::class.java).toList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun clearAllData(scope: CoroutineScope) {
        scope.launch {
            context.dataStore.edit {
                it.clear()
            }
        }
    }

    fun clear(scope: CoroutineScope, key: Preferences.Key<*>) {
        scope.launch {
            context.dataStore.edit {
                if (it.contains(key))
                    it.remove(key)
            }
        }
    }
}
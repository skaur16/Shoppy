package com.example.shoppyapp.features

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import java.io.File

class DataStoreUtil(
    private val dataStore: DataStore<Preferences>
) {
    companion object{
        fun create(context : Context) : DataStoreUtil {
         val datastore = PreferenceDataStoreFactory.create{
             File(context.filesDir, "datastore/datastore.preferences_pb")
         }
            return DataStoreUtil(datastore)
        }
    }

    suspend inline fun <reified T>getData(key : String) : T?{
        val str = getSerializedData(key) ?: return null
        return Gson().fromJson(str,T::class.java)
    }

    suspend inline fun <reified T> setData(key : String, value : T){
        setSerializedData(key, Gson().toJson(value))
    }

    @PublishedApi
    internal suspend fun getSerializedData(key: String): String? {
        return dataStore.data.first()[stringPreferencesKey(key)]
    }

    @PublishedApi
    internal suspend fun setSerializedData(key: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }
}
package com.example.androidbroadcastreceiver.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class UserManager(val context: Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun save(key:String, value:String){
        val dataStoreKey= stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] =value
        }
    }

    suspend fun read(key:String):String?{
        val dataStoreKey= stringPreferencesKey(key)
        /*val dataStoreKey= stringPreferencesKey(key)
        val preferences= user
        context.dataStore.edit {
            it[dataStoreKey] =value
        }*/
        val prefs = context.dataStore.data.first()

        return prefs[dataStoreKey]
    }

    val statusFlow:Flow<String> = context.dataStore.data.map {
        it[STATUS] ?: ""
    }

    companion object{
        val STATUS = stringPreferencesKey("STATUS")
    }
}
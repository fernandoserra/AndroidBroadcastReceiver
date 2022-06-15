package com.example.androidbroadcastreceiver.utils

import android.content.Context

class SharedPreferences (context: Context) {

    private val sharedPreferences = context.getSharedPreferences("PreferencesApp",0)

    fun putValue(Key:String, value: String){
        sharedPreferences.edit().putString(Key,value).apply()
    }

    fun getValues(Key: String): String? {
        return  sharedPreferences.getString(Key,"")
    }

    fun getAllValues(): MutableMap<String, *>? {
        return  sharedPreferences.all
    }

}
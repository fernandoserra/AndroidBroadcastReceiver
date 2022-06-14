package com.example.androidbroadcastreceiver.utils

import android.content.Context

class SharedPreferences (context: Context) {

    private val sharedPreferences = context.getSharedPreferences("PreferencesApp",0)

    fun putBoolean(Key:String, value: Boolean){
        sharedPreferences.edit().putBoolean(Key,value).apply()
    }

    fun getBoolean(Key: String):Boolean{
        return  sharedPreferences.getBoolean(Key,false)
    }

}
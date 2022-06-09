package com.example.androidbroadcastreceiver.ui.dashboard

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {

    init {
        Log.i(TAG, "-> Services running")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "-> onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "-> onDestroy")
    }

    //Metodo que nos permite enlazar algun otro componente con nuestro servicio
    override fun onBind(p0: Intent?): IBinder? = null

    companion object{
        const val TAG = "MyService"
    }
}
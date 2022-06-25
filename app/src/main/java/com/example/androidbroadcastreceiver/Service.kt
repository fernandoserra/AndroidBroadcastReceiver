package com.example.androidbroadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class Service : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, ":::START::: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, ":::DESTROY::: ")
        super.onDestroy()
    }
    
    companion object{
        const val TAG = "Service"
    }
}
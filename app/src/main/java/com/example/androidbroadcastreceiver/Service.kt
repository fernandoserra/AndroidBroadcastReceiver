package com.example.androidbroadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

class Service : Service() {

    val handler= Handler()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, ":::START::: ")

        var counter =0

        handler.apply {
            var runnable= object : Runnable{
                override fun run() {
                    counter ++
                    Log.d(TAG, "run: $counter")
                    postDelayed(this, 1000)
                }
            }
            postDelayed(runnable,1000)
        }


        return START_STICKY //si el sistema mata el servicio se vuelva a crear
        //return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, ":::DESTROY::: ")
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
    
    companion object{
        const val TAG = "Service"
    }
}
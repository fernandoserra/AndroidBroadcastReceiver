package com.example.androidbroadcastreceiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi


class Service : Service() {

    val handler= Handler()

    private val myBinder = MyLocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        /*Thread {
            while (true) {
                Log.e("Service", "Service is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()*/

        val CHANNELID = "Foreground Service ID"
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANNELID)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_launcher_background)

        startForeground(1001, notification.build())

        return super.onStartCommand(intent, flags, startId)

        //return START_STICKY //si el sistema mata el servicio se vuelva a crear
        //return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, ":::DESTROY::: ")
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }


    inner class MyLocalBinder : Binder() {
        fun getService() : Service {
            return this@Service
        }
    }



    companion object{
        const val TAG = "Service"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_id"
    }
}
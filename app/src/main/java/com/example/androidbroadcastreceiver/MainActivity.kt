package com.example.androidbroadcastreceiver

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidbroadcastreceiver.databinding.ActivityMainBinding
import com.example.androidbroadcastreceiver.ui.home.HomeFragment
import com.example.androidbroadcastreceiver.utils.NetworkConnection
import com.example.androidbroadcastreceiver.utils.UserManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var myService: MyService? = null
    var isBound = false

    private val mConection = object : ServiceConnection{
        override fun onServiceConnected(className: ComponentName?, binder: IBinder?) {
            val service = binder as MyService.MyLocalBinder
            myService = service.getService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }

    }

    //private lateinit var  userManager : UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val  userManager= context?.let { UserManager(it) }


       val  userManager=UserManager(this)

        //observeStatus()

        if(isBound){
            lifecycleScope.launch {
                myService?.getProgress()?.collect {
                    Log.i("MainActivity", "onCreate-->: $it")
                }
            }
        }


        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected->
            if(isConnected){
                //Toast.makeText(this, "Si tiene conexion", Toast.LENGTH_SHORT).show()
                val snack = Snackbar.make(binding.root,"Si tiene conexion",Snackbar.LENGTH_SHORT)
                snack.setAction("Cerrar"){
                    snack.dismiss()
                }
                snack.show()
            }else{
                //Toast.makeText(this, "Error en la conexion", Toast.LENGTH_SHORT).show()
                val snack = Snackbar.make(binding.root,"Error en la conexion",Snackbar.LENGTH_SHORT)
                snack.setAction("Cerrar"){
                    snack.dismiss()
                }
                snack.show()
            }
        })

        val navView: BottomNavigationView = binding.navView

        if(!foregroundServiceRunning()) {
            val intent = Intent(this,MyService::class.java)
            startService(intent)
            bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        userManager.statusFlow.asLiveData().observe(this) {
            Log.i("MainActivity", "statusFlow: $it" )
        }

    }

    /*private fun  observeStatus(){
        userManager.statusFlow.asLiveData().observe(this) {
            Log.i("MainActivity", "statusFlow: $it" )
        }

    }*/

    fun foregroundServiceRunning(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (MyService::class.java.getName() == service.service.className) {
                return true
            }
        }
        return false
    }

    fun showData(){
        //val currentTime = myService?.getCurrentTime()
        /*lifecycleScope.launch {

        }*/
        val currentTime = myService?.getTime()
        Log.i("MainActivity", "showData: $currentTime")
    }


    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName,
                                        service: IBinder
        ) {
            val binder2 = service as MyService.MyLocalBinder
            myService = binder2.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyService::class.java).also {
            bindService(it,mConection,Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(mConection)
    }
}
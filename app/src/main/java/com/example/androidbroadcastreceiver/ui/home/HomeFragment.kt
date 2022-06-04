package com.example.androidbroadcastreceiver.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.androidbroadcastreceiver.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val homeViewModel : HomeViewModel by activityViewModels ()
    //private val homeViewModel : HomeViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,   savedInstanceState: Bundle? ): View {

        //val homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val textView: TextView = binding.textHome

        homeViewModel.text2.observe(viewLifecycleOwner){
            Log.i(TAG, "view model: $it")
            textView.text = it
        }




        Handler().postDelayed({
            Log.i(TAG, "Ejecutando postDelayed ")
            homeViewModel.showBatery2()

        }, 5000)

        //homeViewModel.showBatery(batteryLevel.toString())

        /*homeViewModel.showBatery().observe(viewLifecycleOwner) {
            Log.i(TAG, "view model: $it")
            textView.text = it
        }*/

        /*textView.text = homeViewModel.text.toString()
        homeViewModel.text.observe(viewLifecycleOwner) {
            Log.i(TAG, "view model: $it")
            textView.text = it
        }*/

        /*
        homeViewModel.baterry.observe(viewLifecycleOwner){
            textView.text = it
        }*/

        return root
    }

    override fun onResume() {
        super.onResume()
        context?.registerReceiver(getBatteryLevel, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private val getBatteryLevel = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            //Verificando que el valor no sea null
            batteryLevel.let {
                Log.i(TAG, "Porcentaje de bateria $batteryLevel")
                var valueBattery:String = batteryLevel.toString()
               // homeViewModel.text = LiveData<valueBattery>()

                /* var _text22 = MutableLiveData<String>().apply {
                    value =  batteryLevel.toString()
                }*/

                /*
                homeViewModel.text = MutableLiveData<String>().apply {
                    value =  batteryLevel.toString()
                }
                * */



            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.unregisterReceiver(getBatteryLevel)
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}
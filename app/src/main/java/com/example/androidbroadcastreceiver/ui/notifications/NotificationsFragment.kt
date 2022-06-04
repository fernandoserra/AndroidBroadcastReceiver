package com.example.androidbroadcastreceiver.ui.notifications

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
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.androidbroadcastreceiver.databinding.FragmentNotificationsBinding
import com.example.androidbroadcastreceiver.ui.home.HomeFragment
import com.example.androidbroadcastreceiver.ui.home.HomeViewModel

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null


    private val notificationsViewModel : NotificationsViewModel by activityViewModels ()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View {
        //val notificationsViewModel =
         //   ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        val btn: Button = binding.button

        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        btn.setOnClickListener {
            Log.i("NotificationsFragment", "Ejecutando boton ")
            notificationsViewModel.changeValue("111")
        }

        /*Handler().postDelayed({
            notificationsViewModel.changeValue("222")
        }, 5000)*/
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
                Log.i("NotificationsFragment", "Porcentaje de bateria $batteryLevel")
                var valueBattery:String = batteryLevel.toString()
                notificationsViewModel.changeValue(batteryLevel.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.unregisterReceiver(getBatteryLevel)
        _binding = null
    }
}
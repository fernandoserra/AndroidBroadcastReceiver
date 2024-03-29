package com.example.androidbroadcastreceiver.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidbroadcastreceiver.databinding.FragmentDashboardBinding
import com.example.androidbroadcastreceiver.ui.home.FragmentBase

class DashboardFragment : FragmentBase() { //Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnIniciar.setOnClickListener {
            Intent(context,MyService::class.java).also {
                activity?.startService(it)
                Log.i(TAG, "Servicio Iniciado fragment ")
            }
        }

        binding.btnFinalizar.setOnClickListener {
            Intent(context,MyService::class.java).also {
                activity?.stopService(it)
                Log.i(TAG, "Servicio Finalizado fragment ")
            }
        }

        // region btn
        binding.btnMSG2.setOnClickListener {
            show()
        }
        // endregion


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i(TAG, "onDestroyView Fragment ")
    }

    companion object{
        const val TAG = "DashboardFragment"
    }
}
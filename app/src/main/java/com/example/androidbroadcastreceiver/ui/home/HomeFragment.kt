package com.example.androidbroadcastreceiver.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidbroadcastreceiver.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,   savedInstanceState: Bundle? ): View {

        val homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner){
            Log.i(TAG, "view model: $it")
            textView.text = it
        }

        binding.btnValue.setOnClickListener {
            Toast.makeText(context, "Almacenando valor", Toast.LENGTH_SHORT).show()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}
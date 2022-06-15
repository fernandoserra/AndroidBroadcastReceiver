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
import com.example.androidbroadcastreceiver.utils.SharedPreferences

class HomeFragment : Fragment() {

    private lateinit var preferencesProvider: SharedPreferences

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,   savedInstanceState: Bundle? ): View {
        preferencesProvider= SharedPreferences(requireContext())
        val homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner){
            Log.i(TAG, "view model: $it")
            textView.text = it
        }

        binding.btnValue.setOnClickListener {
            Toast.makeText(context, "Key::: ${binding.editTextKey.text}  Value::: ${binding.editTextValue.text}", Toast.LENGTH_SHORT).show()
            preferencesProvider.putValue(binding.editTextKey.text.toString(), binding.editTextValue.text.toString())
        }

        binding.btnGetValue.setOnClickListener {
            Log.i(TAG, "BUSCANDO Key::: ${binding.editTextKey.text} ")
           val show = preferencesProvider.getValues(binding.editTextKey.text.toString()).toString()
            Toast.makeText(context, "valor: $show", Toast.LENGTH_SHORT).show()
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
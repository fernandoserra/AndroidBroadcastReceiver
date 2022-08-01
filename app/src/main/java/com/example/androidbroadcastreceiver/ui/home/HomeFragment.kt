package com.example.androidbroadcastreceiver.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.androidbroadcastreceiver.MainActivity
import com.example.androidbroadcastreceiver.MyService
import com.example.androidbroadcastreceiver.databinding.FragmentHomeBinding
import com.example.androidbroadcastreceiver.utils.SharedPreferences
import com.example.androidbroadcastreceiver.utils.UserManager
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var preferencesProvider: SharedPreferences

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    //DataStore
    //private lateinit var dataStore:DataStore<Preferences>

    private lateinit var  userManager : UserManager

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,   savedInstanceState: Bundle? ): View {
        preferencesProvider= SharedPreferences(requireContext())
        val homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //dataStore = createDataStore(name ="settings")

       //val  userManager= context?.let { UserManager(it) }

        userManager= UserManager(requireContext())



         userManager.statusFlow.asLiveData().observe(viewLifecycleOwner) {
              Log.i(TAG, "statusFlow--->: $it" )
             binding.showCount.text="$it"
          }



        val root: View = binding.root

        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner){
            Log.i(TAG, "view model: $it")
            textView.text = it
        }

        binding.btnValue.setOnClickListener {
            Toast.makeText(context, "Key::: ${binding.editTextKey.text}  Value::: ${binding.editTextValue.text}", Toast.LENGTH_SHORT).show()

            //preferencesProvider.putValue(binding.editTextKey.text.toString(), binding.editTextValue.text.toString())

            //DataStore
            lifecycleScope.launch{
                userManager.save(binding.editTextKey.text.toString(),binding.editTextValue.text.toString())
            }
        }

       /* userManager.statusFlow.asLiveData().observe(viewLifecycleOwner) {
            Log.i(TAG, "statusFlow: $it" )
        }*/

        binding.btnGetValue.setOnClickListener {
            Log.i(TAG, "BUSCANDO Key::: ${binding.editTextKey.text} ")
            /*val show = preferencesProvider.getValues(binding.editTextKey.text.toString()).toString()
            Toast.makeText(context, "valor: $show", Toast.LENGTH_SHORT).show()*/

            //DataStore
            /*lifecycleScope.launch{
                val show = userManager?.read(binding.editTextKey.text.toString())
                Toast.makeText(context, "valor: $show", Toast.LENGTH_SHORT).show()
            }*/
        }

        binding.btnService.setOnClickListener {

            val intent = Intent(context, MyService::class.java)
            activity?.stopService(intent)
        }
        binding.showTime.setOnClickListener {
            (activity as? MainActivity)?.showData()
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
package com.example.androidbroadcastreceiver.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.androidbroadcastreceiver.utils.UserManager

class HomeViewModel (application: Application) : AndroidViewModel(application) {



    private val _text = MutableLiveData<String>().apply {
        value = "Home"
    }
    var text: LiveData<String> = _text

}
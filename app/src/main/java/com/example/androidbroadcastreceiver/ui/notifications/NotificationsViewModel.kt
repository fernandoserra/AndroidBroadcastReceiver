package com.example.androidbroadcastreceiver.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    var text: LiveData<String> = _text

    fun changeValue (value:String){
        Log.i("NotificationsViewModel", "Ejecutando changeValue ")
        _text.postValue(value)
    }
}
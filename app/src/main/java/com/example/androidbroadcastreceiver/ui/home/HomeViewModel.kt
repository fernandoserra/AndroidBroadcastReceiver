package com.example.androidbroadcastreceiver.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "99"
    }
    var text: LiveData<String> = _text


    var baterry = MutableLiveData<String>()

    var baterryValue = MutableLiveData<String>()


    var text2: LiveData<String> = _text

    fun showBatery2() {
        Log.i("HomeViewModel", "EJECUTANDO showBatery22:")
        baterry.postValue("sippioio")

        Log.i("HomeViewModel", "showBatery2: ")

        /*Log.i("HomeViewModel", "EJECUTANDO showBatery22:")
        text2=  MutableLiveData<String>().apply {
            value = "sipiii"
        }*/
    }


    fun showBatery(value3:String) : LiveData<String>{
        Log.i("HomeViewModel", "EJECUTANDO showBatery: $value3")
        return  MutableLiveData<String>().apply {
            value = value3.toString()
        }
    }


    fun updateBatery(value2:String) {
        Log.i("HomeViewModel", "EJECUTANDO updateBatery: $value2")
        text2 = MutableLiveData<String>().apply {
           value = value2
       }
        baterryValue= text2 as MutableLiveData<String>
        Log.i("HomeViewModel", "updateBatery: $baterryValue")
    }

    fun updateBatery2(value2:String): LiveData<String> {
        Log.i("HomeViewModel", "EJECUTANDO updateBatery: $value2")
       var  text2 = MutableLiveData<String>().apply {
            value = value2
        }



        return text2
    }






}
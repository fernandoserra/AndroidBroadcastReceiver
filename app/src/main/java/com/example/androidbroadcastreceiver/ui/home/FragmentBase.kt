package com.example.androidbroadcastreceiver.ui.home

import android.widget.Toast
import androidx.fragment.app.Fragment

open class FragmentBase : Fragment() {

    fun show() {
        Toast.makeText(context, "Function MSG", Toast.LENGTH_SHORT).show()
    }

}
package com.example.perpustakaan.ui.base

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

interface View {
    fun onAttachView()
    fun onDetachView()
    fun showMessage(context: Context, message:String, timeLength:Int){
        Toast.makeText(context,message,timeLength).show()
    }
}
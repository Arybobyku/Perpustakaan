package com.example.perpustakaan.helper

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

object Helper {
    @SuppressLint("MissingPermission")
    @JvmStatic
    fun isNetworkConnected(context: Context):Boolean{
        context.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            return info !== null && info.isConnected
        }
    }
}
package com.example.perpustakaan.ui.activity.register

import com.example.perpustakaan.ui.base.View

interface RegisterView:View{
    fun getActivity(): RegisterActivity
    fun showLoading(show: Boolean)
    fun registerSuccess()
}
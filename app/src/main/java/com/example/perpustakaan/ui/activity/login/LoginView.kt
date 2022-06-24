package com.example.perpustakaan.ui.activity.login

import com.example.perpustakaan.ui.base.View


interface LoginView: View {
    fun getActivity():LoginActivity
    fun showLoading(show: Boolean)
    fun loginSuccess()
}
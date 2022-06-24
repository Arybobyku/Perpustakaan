package com.example.perpustakaan.ui.base

interface Presenter<T:View> {
    fun onAttach(view:T)
    fun onDetach()
}
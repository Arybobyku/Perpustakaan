package com.example.perpustakaan.service

import android.util.Log
import com.example.perpustakaan.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable

object AuthService {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(email: String, password: String):Task<AuthResult>{
      return  auth.createUserWithEmailAndPassword(
            email,
            password
        )
    }

    fun signIn(email: String, password: String):Task<AuthResult>{
        return auth.signInWithEmailAndPassword(email,password)
    }

}
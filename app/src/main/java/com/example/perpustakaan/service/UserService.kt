package com.example.perpustakaan.service

import android.util.Log
import com.example.perpustakaan.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

object UserService {
    private val userReference =FirebaseFirestore.getInstance().collection("User")
    fun setUser(user:User):Task<Void>{
        return userReference.document(user.id!!).set(user)
    }

    fun getUserById(uuid:String):Task<DocumentSnapshot>{
       return userReference.document(uuid).get()
    }
}
package com.example.perpustakaan.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class  User:RealmObject(){
    lateinit var id:String
    lateinit var fullName:String
    lateinit var email:String;
}

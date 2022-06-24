package com.example.perpustakaan.helper

import com.example.perpustakaan.model.User
import io.realm.Realm
import io.realm.RealmQuery

object RealmHelper {
    private val realm: Realm
        get() {
            return Realm.getDefaultInstance()
        }
    //Query Get Me
    fun me(r: Realm): RealmQuery<User> {
        return r.where(User::class.java)
    }
    fun saveMe(me:User):Boolean{
        val r = realm
        deleteAllMe()
        r.executeTransaction {
            it.insertOrUpdate(me)
        }
        r.close()
        return true
    }
    fun deleteAllMe(){
        val r = realm
        r.executeTransaction {
            r.delete(User::class.java)
        }
        r.close()
    }
    //Query Get First Data
    fun getMe(): User? {
        val r = realm
        val selectFirstData = me(r).findFirst()
        var me: User? = null
        selectFirstData.let {
            me = r.copyFromRealm(selectFirstData)
        }
        r.close()
        return me
    }
    fun getCount(): Int {
        val r = realm
        val selectAllAuth = me(r).findAll()
        var count: Int = 0
        var user: List<User>? = null
        selectAllAuth.let {
            user = it
        }
        count = user!!.size
        r.close()
        return count
    }
}
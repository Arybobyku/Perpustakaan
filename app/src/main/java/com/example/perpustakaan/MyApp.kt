package com.example.perpustakaan

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp:Application(), Application.ActivityLifecycleCallbacks{

    var foregroundActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmName: String = "realmdb"
        val config = RealmConfiguration
            .Builder()
            .name(realmName)
            .schemaVersion(4)
            .deleteRealmIfMigrationNeeded()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        //register activity lifecycle
        registerActivityLifecycleCallbacks(this)
        //set this config as default
        Realm.setDefaultConfiguration(config)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }


    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

}
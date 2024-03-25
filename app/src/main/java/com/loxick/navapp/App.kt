package com.loxick.navapp

import android.app.Application
import android.util.Log

class App:Application() {
    val database by lazy {
        Log.d("info:::","DbAPP")
        MainDB.createDataBase(this)
    }
}
package com.example.loginproject

import android.app.Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        SecuredSharedPrefference.init(this)
    }
}
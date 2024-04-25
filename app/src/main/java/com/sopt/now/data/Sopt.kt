package com.sopt.now.data

import android.app.Application

class Sopt: Application() {
    override fun onCreate() {
        super.onCreate()
        UserRepository.init(this)
    }
}
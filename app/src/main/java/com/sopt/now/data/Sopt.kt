package com.sopt.now.data

import android.app.Application

class Sopt: Application() {
    private lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(this)
    }
}
package com.sopt.now.compose

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.sopt.now.compose.data.UserRepository

class SoptApp: Application() {
    override fun onCreate() {
        super.onCreate()
        preferenceInstance = getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)
        userRepository = UserRepository(preferenceInstance)
    }

    companion object {
        lateinit var preferenceInstance: SharedPreferences
        lateinit var userRepository: UserRepository
    }
}
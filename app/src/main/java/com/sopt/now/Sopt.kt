package com.sopt.now

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.sopt.now.data.UserRepository

class Sopt : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        preferenceInstance = getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)
        userRepository = UserRepository(preferenceInstance)
    }

    companion object {
        lateinit var instance: Sopt
        lateinit var preferenceInstance: SharedPreferences
        lateinit var userRepository: UserRepository
    }
}
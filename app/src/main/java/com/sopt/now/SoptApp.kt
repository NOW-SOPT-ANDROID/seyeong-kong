package com.sopt.now

import android.app.Application
import android.content.Context

class SoptApp : Application() {
    lateinit var serviceLocator: ServiceLocator

    companion object {
        @Volatile
        private lateinit var instance: SoptApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        serviceLocator = ServiceLocator(applicationContext)
    }
}
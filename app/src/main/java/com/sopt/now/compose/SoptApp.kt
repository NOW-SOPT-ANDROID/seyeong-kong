package com.sopt.now.compose

import android.app.Application
import android.content.Context

class SoptApp : Application() {
    lateinit var serviceLocator: ServiceLocator

    companion object {
        @Volatile
        private lateinit var instance: SoptApp

        val serviceLocatorInstance: ServiceLocator
            get() = instance.serviceLocator

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
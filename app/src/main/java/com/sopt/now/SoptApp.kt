package com.sopt.now

import android.app.Application
import android.content.Context

class SoptApp: Application() {
    lateinit var appContainer: AppContainer

    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(applicationContext)
    }

    companion object {
        private var instance: SoptApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}
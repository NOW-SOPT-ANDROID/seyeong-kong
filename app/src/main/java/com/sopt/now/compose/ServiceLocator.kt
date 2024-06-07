package com.sopt.now.compose

import android.content.Context
import com.sopt.now.compose.data.UserLocalDataSource
import com.sopt.now.compose.data.UserRemoteDataSource
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.service.ServicePool

class ServiceLocator(context: Context) {
    private val preferenceInstance by lazy {
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)
    }

    private val userLocalDataSource by lazy {
        UserLocalDataSource(preferenceInstance)
    }

    private val userRemoteDataSource by lazy {
        UserRemoteDataSource(ServicePool.authService)
    }

    val userRepository by lazy {
        UserRepository(userLocalDataSource, userRemoteDataSource)
    }
}
package com.sopt.now

import android.content.Context
import com.sopt.now.data.UserLocalDataSource
import com.sopt.now.data.UserRemoteDataSource
import com.sopt.now.data.UserRepository
import com.sopt.now.data.friend.FriendDatabase
import com.sopt.now.data.friend.FriendsRepository
import com.sopt.now.data.friend.OfflineFriendsRepository
import com.sopt.now.network.service.ServicePool
import com.sopt.now.util.AppViewModelFactory

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

    private val friendDao by lazy {
        FriendDatabase.getDatabase(context).friendDao()
    }

    val friendsRepository: FriendsRepository by lazy {
        OfflineFriendsRepository(friendDao)
    }

    val appViewModelFactory by lazy {
        AppViewModelFactory(userRepository, friendsRepository)
    }
}
package com.sopt.now

import android.content.Context
import android.content.SharedPreferences
import com.sopt.now.data.UserRepository
import com.sopt.now.data.friend.FriendDatabase
import com.sopt.now.data.friend.FriendsRepository
import com.sopt.now.data.friend.OfflineFriendsRepository

class AppContainer(private val context: Context) {
    private val preferenceInstance: SharedPreferences by lazy {
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)
    }

    val userRepository: UserRepository by lazy {
        UserRepository(preferenceInstance)
    }

    fun provideFriendsRepository(): FriendsRepository {
        return OfflineFriendsRepository(FriendDatabase.getDatabase(context).friendDao())
    }
}
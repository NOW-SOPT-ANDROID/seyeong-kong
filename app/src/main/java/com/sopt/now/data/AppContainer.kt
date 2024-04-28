package com.sopt.now.data

import android.content.Context

class AppContainer(private val context: Context) {
    val friendsRepository: FriendsRepository by lazy {
        OfflineFriendsRepository(FriendDatabase.getDatabase(context).friendDao())
    }
}
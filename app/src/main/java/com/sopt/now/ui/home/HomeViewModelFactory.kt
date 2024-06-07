package com.sopt.now.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.friend.FriendsRepository

class HomeViewModelFactory(private val friendsRepository: FriendsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(friendsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
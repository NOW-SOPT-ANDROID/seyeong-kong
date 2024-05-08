package com.sopt.now.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.friend.FriendsRepository
import com.sopt.now.util.viewModelFactory

class HomeViewModelFactory(private val repository: FriendsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelFactory { HomeViewModel(repository) }.create(modelClass)
    }
}

package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.User
import com.sopt.now.data.UserRepository

class MypageViewModel(private val userRepository: UserRepository): ViewModel() {
    val userLiveData = MutableLiveData<User?>()

    init {
        userRepository.getUserData()
    }

    fun logout() {
        userRepository.clearUserData()
        userLiveData.value = null
    }
}
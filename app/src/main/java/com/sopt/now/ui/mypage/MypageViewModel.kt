package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.User
import com.sopt.now.data.UserRepository

class MypageViewModel: ViewModel() {
    val userLiveData = MutableLiveData<User?>()

    init {
        userLiveData.value = UserRepository.getUserData()
    }

    fun logout() {
        UserRepository.clearUserData()
        userLiveData.value = null
    }
}
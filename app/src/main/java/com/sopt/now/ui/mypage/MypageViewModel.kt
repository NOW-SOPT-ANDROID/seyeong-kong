package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.SoptApp
import com.sopt.now.data.User

class MypageViewModel: ViewModel() {
    val userLiveData = MutableLiveData<User?>()

    init {
        userLiveData.value = SoptApp().appContainer.userRepository.getUserData()
    }

    fun logout() {
        SoptApp().appContainer.userRepository.clearUserData()
        userLiveData.value = null
    }
}
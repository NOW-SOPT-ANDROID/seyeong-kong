package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Sopt
import com.sopt.now.data.User

class MypageViewModel: ViewModel() {
    val userLiveData = MutableLiveData<User?>()

    init {
        userLiveData.value = Sopt.userRepository.getUserData()
    }

    fun logout() {
        Sopt.userRepository.clearUserData()
        userLiveData.value = null
    }
}
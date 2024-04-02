package com.sopt.now

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    val userId = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userNickname = MutableLiveData<String>()
    val userMbti = MutableLiveData<String>()
}
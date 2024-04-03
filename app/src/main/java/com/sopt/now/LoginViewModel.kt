package com.sopt.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    var id: String? = null
    var pw: String? = null
    var nickname: String? = null
    var mbti: String? = null

    fun login(inputId: String, inputPw: String) {
        _loginResult.value = (inputId == id && inputPw == pw)
    }

    fun setUserDetails(
        receivedId: String,
        receivedPw: String,
        receivedNickname: String,
        receivedMbti: String
    ) {
        id = receivedId
        pw = receivedPw
        nickname = receivedNickname
        mbti = receivedMbti
    }
}
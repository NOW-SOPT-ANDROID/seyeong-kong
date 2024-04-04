package com.sopt.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult
    var user: User? = null

    fun login(inputId: String, inputPw: String) {
        _loginResult.value = (inputId == user?.id && inputPw == user?.password)
    }

    fun setUserDetails(user: User) {
        this.user = user
    }
}
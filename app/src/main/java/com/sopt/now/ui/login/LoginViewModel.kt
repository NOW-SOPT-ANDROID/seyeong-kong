package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Sopt

class LoginViewModel: ViewModel() {
    val loginSuccess = MutableLiveData<Boolean>()

    fun login(id: String, pw: String) {
        val user = Sopt.userRepository.getUserData()
        if(user != null && id == user.id && pw == user.password) {
            Sopt.userRepository.setUserLoggedIn(true)
            loginSuccess.value = true
        } else {
            loginSuccess.value = false
        }
    }
}
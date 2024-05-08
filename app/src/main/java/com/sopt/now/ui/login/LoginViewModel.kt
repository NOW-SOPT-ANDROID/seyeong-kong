package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.User
import com.sopt.now.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val loginSuccess = MutableLiveData<Boolean>()
    val userData = MutableLiveData<User?>()

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        userData.value = userRepository.getUserData()
    }

    fun login(id: String, pw: String) {
        userData.value?.let {
            if(id == it.id && pw == it.password) {
                userRepository.setUserLoggedIn(true)
                loginSuccess.value = true
            } else {
                loginSuccess.value = false
            }
        } ?: run {
            loginSuccess.value = false
        }
    }
}
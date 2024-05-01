package com.sopt.now.compose.ui.login

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun loginUser(id: String, password: String): Boolean {
        return userRepository.isValidateUser(id, password)
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        userRepository.setUserLoggedIn(isLoggedIn)
    }

    fun isUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }
}
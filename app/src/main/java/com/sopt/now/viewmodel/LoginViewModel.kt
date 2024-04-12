package com.sopt.now.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.User
import com.sopt.now.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult
    var user: User? = null

    fun login(inputId: String, inputPw: String) {
        user = userRepository.getUserDetails()
        _loginResult.value = (inputId == user?.id && inputPw == user?.password)
    }

    fun setUserDetails(user: User) {
        this.user = user
        userRepository.saveUserDetails(user)
    }
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

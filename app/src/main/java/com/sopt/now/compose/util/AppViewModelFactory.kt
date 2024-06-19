package com.sopt.now.compose.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.changePassword.ChangePasswordViewModel
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageViewModel
import com.sopt.now.compose.ui.signup.SignupViewModel

class AppViewModelFactory(
    private val userRepository: UserRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(MypageViewModel::class.java) -> {
                MypageViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ChangePasswordViewModel::class.java) -> {
                ChangePasswordViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
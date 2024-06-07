package com.sopt.now.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.UserRepository
import com.sopt.now.data.friend.FriendsRepository
import com.sopt.now.ui.change_password.ChangePasswordViewModel
import com.sopt.now.ui.home.HomeViewModel
import com.sopt.now.ui.login.LoginViewModel
import com.sopt.now.ui.mypage.MypageViewModel
import com.sopt.now.ui.signup.SignupViewModel

class AppViewModelFactory(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
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
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(friendsRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
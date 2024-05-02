package com.sopt.now.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.ch_password.ChPasswordViewModel
import com.sopt.now.compose.ui.follower.FollowerViewModel
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.signup.SignupViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    userRepository: UserRepository,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
    chPasswordViewModel: ChPasswordViewModel,
    followerViewmodel: FollowerViewModel,
) {
    val startDestination = if (userRepository.isUserLoggedIn()) {
        "home"
    } else {
        "login"
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNavGraph(
            navController,
            userRepository,
            signupViewModel,
            loginViewModel,
            chPasswordViewModel,
            followerViewmodel
        )
    }
}


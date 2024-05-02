package com.sopt.now.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.signup.SignupViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    userRepository: UserRepository,
    user: User?,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
) {
    val startDestination = if (user != null || userRepository.isUserLoggedIn()) {
        "home"
    } else {
        "login"
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNavGraph(navController, userRepository, user, signupViewModel, loginViewModel)
    }
}


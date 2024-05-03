package com.sopt.now.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.SoptApp
import com.sopt.now.compose.ui.ch_password.ChPasswordViewModel
import com.sopt.now.compose.ui.follower.FollowerViewModel
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageViewModel
import com.sopt.now.compose.ui.signup.SignupViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
    chPasswordViewModel: ChPasswordViewModel,
    mypageViewModel: MypageViewModel,
    followerViewModel: FollowerViewModel,
) {
    val startDestination = if (SoptApp.userRepository.isUserLoggedIn()) {
        "home"
    } else {
        "login"
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNavGraph(
            navController,
            signupViewModel,
            loginViewModel,
            chPasswordViewModel,
            mypageViewModel,
            followerViewModel
        )
    }
}


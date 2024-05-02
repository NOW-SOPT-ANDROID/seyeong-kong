package com.sopt.now.compose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageScreen
import com.sopt.now.compose.ui.search.SearchScreen
import com.sopt.now.compose.ui.signup.SignupScreen
import com.sopt.now.compose.ui.signup.SignupViewModel

fun NavGraphBuilder.addNavGraph(
    navController: NavHostController,
    userRepository: UserRepository,
    user: User?,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
) {
    composable("home") { HomeScreen() }
    composable("search") { SearchScreen() }
    composable("login") { LoginScreen(navController, loginViewModel) }
    composable("signup") { SignupScreen(navController, signupViewModel) }
    composable("mypage") { MypageScreen(navController, userRepository, user) }
}

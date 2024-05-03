package com.sopt.now.compose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.ch_password.ChPasswordScreen
import com.sopt.now.compose.ui.ch_password.ChPasswordViewModel
import com.sopt.now.compose.ui.follower.FollowerScreen
import com.sopt.now.compose.ui.follower.FollowerViewModel
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageScreen
import com.sopt.now.compose.ui.mypage.MypageViewModel
import com.sopt.now.compose.ui.search.SearchScreen
import com.sopt.now.compose.ui.signup.SignupScreen
import com.sopt.now.compose.ui.signup.SignupViewModel

fun NavGraphBuilder.addNavGraph(
    navController: NavHostController,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
    chPasswordViewModel: ChPasswordViewModel,
    mypageViewModel : MypageViewModel,
    followerViewModel: FollowerViewModel,
) {
    composable("home") { HomeScreen() }
    composable("search") { SearchScreen() }
    composable("login") { LoginScreen(navController, loginViewModel) }
    composable("signup") { SignupScreen(navController, signupViewModel) }
    composable("mypage") { MypageScreen(navController, mypageViewModel) }
    composable("chPassword") { ChPasswordScreen(navController, chPasswordViewModel) }
    composable("follower") { FollowerScreen(followerViewModel) }
}

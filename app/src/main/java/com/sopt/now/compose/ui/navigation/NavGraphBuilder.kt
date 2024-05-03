package com.sopt.now.compose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sopt.now.compose.ui.ch_password.ChPasswordScreen
import com.sopt.now.compose.ui.follower.FollowerScreen
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.mypage.MypageScreen
import com.sopt.now.compose.ui.search.SearchScreen
import com.sopt.now.compose.ui.signup.SignupScreen

fun NavGraphBuilder.addNavGraph(
    navController: NavHostController
) {
    composable("home") { HomeScreen() }
    composable("search") { SearchScreen() }
    composable("login") { LoginScreen(navController) }
    composable("signup") { SignupScreen(navController) }
    composable("mypage") { MypageScreen(navController) }
    composable("chPassword") { ChPasswordScreen(navController) }
    composable("follower") { FollowerScreen() }
}

package com.sopt.now.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.data.UserRepository

@Composable
fun NavGraph(
    navController: NavHostController,
    userRepository: UserRepository,
) {
    val startDestination = remember {
        if (userRepository.isUserLoggedIn()) {
            "home"
        } else {
            "login"
        }
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNavGraph(
            navController
        )
    }
}
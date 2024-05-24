package com.sopt.now.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.SoptApp

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val startDestination = if (SoptApp.userRepository.isUserLoggedIn()) {
        "home"
    } else {
        "login"
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNavGraph(
            navController
        )
    }
}


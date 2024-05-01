package com.sopt.now.compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageScreen
import com.sopt.now.compose.ui.search.SearchScreen
import com.sopt.now.compose.ui.signup.SignupScreen
import com.sopt.now.compose.ui.signup.SignupViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepository = UserRepository(applicationContext)
        val user = userRepository.getUserData()
        val startDestination = if (userRepository.isUserLoggedIn()) "home" else "login"

        setContent {
            val navController = rememberNavController()
            val loginViewModel = remember { LoginViewModel(userRepository) }
            val signupViewModel = remember { SignupViewModel(application) }
            MainContent(
                navController,
                startDestination,
                user,
                userRepository,
                loginViewModel,
                signupViewModel
            ) { userRepository.clearUserData() }

        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController,
    startDestination: String,
    user: User?,
    userRepository: UserRepository,
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    onLogout: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(icon = Icons.Filled.Home, label = "Home"),
        BottomNavigationItem(icon = Icons.Filled.Search, label = "Search"),
        BottomNavigationItem(icon = Icons.Filled.Person, label = "Mypage")
    )
    val routes = listOf("home", "search", "mypage")


    Scaffold(
        bottomBar = {
            if (currentRoute in routes) {
                NavigationBar {
                    if (currentRoute in routes) {
                        BottomNavigationBar(navController, selectedItem, items, routes)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("search") { SearchScreen() }
            composable("mypage") { MypageScreen(navController, userRepository, user) }
            composable("login") { LoginScreen(navController, loginViewModel) }
            composable("signup") { SignupScreen(navController, signupViewModel) }
        }
    }
}
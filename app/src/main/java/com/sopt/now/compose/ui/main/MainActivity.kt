package com.sopt.now.compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.ch_password.ChPasswordViewModel
import com.sopt.now.compose.ui.follower.FollowerItem
import com.sopt.now.compose.ui.follower.FollowerViewModel
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.navigation.BottomNavigationItem
import com.sopt.now.compose.ui.navigation.BottomNavigationBar
import com.sopt.now.compose.ui.navigation.NavGraph
import com.sopt.now.compose.ui.signup.SignupViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = UserRepository(applicationContext)
        val user = userRepository.getUserData()

        setContent {
            val navController = rememberNavController()
            val loginViewModel = remember { LoginViewModel(userRepository) }
            val signupViewModel = remember { SignupViewModel(userRepository) }
            val chPasswordViewModel = remember { ChPasswordViewModel(userRepository) }
            val followerViewmodel = remember { FollowerViewModel() }

            MainContent(
                navController,
                user,
                userRepository,
                signupViewModel,
                loginViewModel,
                chPasswordViewModel,
                followerViewmodel
            ) { userRepository.logoutUser() }
        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController,
    user: User?,
    userRepository: UserRepository,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
    chPasswordViewModel: ChPasswordViewModel,
    followerViewmodel: FollowerViewModel,
    onLogout: () -> Unit,
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
                    BottomNavigationBar(navController, selectedItem, items, routes)
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navController = navController,
                userRepository = userRepository,
                signupViewModel = signupViewModel,
                loginViewModel = loginViewModel,
                chPasswordViewModel = chPasswordViewModel,
                followerViewmodel = followerViewmodel
            )
        }
    }
}

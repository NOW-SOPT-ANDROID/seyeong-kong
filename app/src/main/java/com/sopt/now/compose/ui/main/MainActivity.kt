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
import com.sopt.now.compose.SoptApp
import com.sopt.now.compose.ui.ch_password.ChPasswordViewModel
import com.sopt.now.compose.ui.follower.FollowerViewModel
import com.sopt.now.compose.ui.login.LoginViewModel
import com.sopt.now.compose.ui.mypage.MypageViewModel
import com.sopt.now.compose.ui.navigation.BottomNavigationItem
import com.sopt.now.compose.ui.navigation.BottomNavigationBar
import com.sopt.now.compose.ui.navigation.NavGraph
import com.sopt.now.compose.ui.signup.SignupViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val loginViewModel = remember { LoginViewModel() }
            val signupViewModel = remember { SignupViewModel() }
            val chPasswordViewModel = remember { ChPasswordViewModel() }
            val myPageViewModel = remember { MypageViewModel() }
            val followerViewModel = remember { FollowerViewModel() }

            MainContent(
                navController,
                signupViewModel,
                loginViewModel,
                chPasswordViewModel,
                myPageViewModel,
                followerViewModel
            ) { SoptApp.userRepository.logoutUser() }
        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController,
    signupViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
    chPasswordViewModel: ChPasswordViewModel,
    mypageViewModel: MypageViewModel,
    followerViewModel: FollowerViewModel,
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
                signupViewModel = signupViewModel,
                loginViewModel = loginViewModel,
                chPasswordViewModel = chPasswordViewModel,
                mypageViewModel = mypageViewModel,
                followerViewModel = followerViewModel
            )
        }
    }
}

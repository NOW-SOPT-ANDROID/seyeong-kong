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
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.ui.navigation.BottomNavigationItem
import com.sopt.now.compose.ui.navigation.BottomNavigationBar
import com.sopt.now.compose.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MainContent(
                navController, userRepository
            )
        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController,
    userRepository: AuthRepository,
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
                userRepository = userRepository
            )
        }
    }
}

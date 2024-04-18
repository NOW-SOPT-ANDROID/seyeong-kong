package com.sopt.now.compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.login.LoginScreen
import com.sopt.now.compose.ui.mypage.MypageScreen
import com.sopt.now.compose.ui.search.SearchScreen
import com.sopt.now.compose.ui.signup.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepository = UserRepository(applicationContext)
        val startDestination = if (userRepository.isUserLoggedIn()) "home" else "login"

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                MainContent(navController, startDestination)
            }
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String,
)

@Composable
fun MainContent(navController: NavHostController, startDestination: String) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedItem by remember { mutableIntStateOf(0) }
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
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                navController.navigate(routes[index]) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
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
            composable("mypage") { MypageScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignupScreen(navController) }
        }
    }
}

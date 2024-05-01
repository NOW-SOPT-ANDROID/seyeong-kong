package com.sopt.now.compose.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String,
)

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    selectedItem: Int,
    items: List<BottomNavigationItem>,
    routes: List<String>
) {
    var selected by remember { mutableIntStateOf(selectedItem) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selected == index,
                onClick = {
                    if (selected != index) {
                        selected = index
                        navController.navigate(routes[index]) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}
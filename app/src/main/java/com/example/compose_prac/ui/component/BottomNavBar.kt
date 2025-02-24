package com.example.compose_prac.ui.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose_prac.ui.Screen.main.MainUiState
import com.example.compose_prac.ui.navigation.screensInBottom
import com.example.compose_prac.ui.uitls.navigateBottom
import androidx.compose.runtime.*
import com.example.compose_prac.ui.navigation.Screen


@Composable
fun BottomNavBar(navController: NavController, uiState: MainUiState) {
    if (uiState.currentScree is Screen.BottomScreen
        || uiState.currentScree is Screen.DrawerScreen
    ) {
        NavigationBar(
            modifier = Modifier.wrapContentSize(),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            screensInBottom.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(screen.icon),
                            contentDescription = screen.bTitle,
                            modifier = Modifier
                        )
                    },
                    label = { Text(screen.title) },
                    selected = currentRoute == screen.bRoute,
                    onClick = {
                        navController.navigateBottom(screen)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Black,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

package com.example.compose_prac.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_prac.data.Wish
import com.example.compose_prac.screen.add.AddEditDetailView
import com.example.compose_prac.screen.home.HomeViewModel
import com.example.compose_prac.screen.home.HomeView

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route){
            HomeView(navController = navController)
        }
        composable(Screen.AddScreen.route){
            AddEditDetailView(wish = Wish(), navController = navController)
        }
    }
}
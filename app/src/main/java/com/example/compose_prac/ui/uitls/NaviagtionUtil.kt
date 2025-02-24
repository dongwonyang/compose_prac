package com.example.compose_prac.ui.uitls

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.compose_prac.ui.navigation.Screen

fun NavController.navigateBottom(screen: Screen) {
    navigate(screen.route) {
        popUpTo(graph.findStartDestination().route ?: return@navigate) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

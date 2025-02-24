package com.example.compose_prac.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.compose_prac.ui.Screen.account.AccountView
import com.example.compose_prac.ui.Screen.browse.BrowseView
import com.example.compose_prac.ui.Screen.home.HomeView
import com.example.compose_prac.ui.Screen.library.LibraryView
import com.example.compose_prac.ui.Screen.main.MainViewModel
import com.example.compose_prac.ui.Screen.subscription.SubscriptionView

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    pd: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)
    ) {
        // Drawer
        composable(route = Screen.DrawerScreen.Account.route) {
            AccountView()
        }

        composable(route = Screen.DrawerScreen.Subscription.route) {
            SubscriptionView()
        }

        // BottomBar
        composable(route = Screen.BottomScreen.Home.route) {
            HomeView()
        }
        composable(route = Screen.BottomScreen.Library.route) {
            LibraryView()
        }
        composable(route = Screen.BottomScreen.Browse.route) {
            BrowseView()
        }


    }
}
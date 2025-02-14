package com.example.compose_prac.ui.navigation

import androidx.annotation.DrawableRes
import com.example.compose_prac.R

sealed class Screen(val title:String, val route: String){

    sealed class DrawerScreen(val dTitle:String, val dRoute:String, @DrawableRes val icon: Int): Screen(dTitle, dRoute){
        data object Account: DrawerScreen(
            dTitle = "Account",
            dRoute = "account",
            icon = R.drawable.ic_account
        )

        data object Subscription: DrawerScreen(
            dTitle = "Subscription",
            dRoute = "subscribe",
            icon = R.drawable.ic_subscribe
        )

        data object AddAccount:DrawerScreen(
            dTitle = "Add Account",
            dRoute = "add_account",
            icon = R.drawable.ic_add_account
        )
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)
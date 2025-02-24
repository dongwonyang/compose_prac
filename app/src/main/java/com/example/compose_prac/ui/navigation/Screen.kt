package com.example.compose_prac.ui.navigation

import androidx.annotation.DrawableRes
import com.example.compose_prac.R

sealed class Screen(val title:String, val route: String){
    sealed class  BottomScreen(
        val bTitle:String, val bRoute:String, @DrawableRes val icon: Int
    ): Screen(bTitle, bRoute){
        data object Home:BottomScreen(
            bTitle = "Home",
            bRoute = "home",
            icon = R.drawable.ic_music_video
        )

        data object Library:BottomScreen(
            bTitle = "Library",
            bRoute = "library",
            icon = R.drawable.ic_subscribe
        )

        data object Browse: BottomScreen(
            bTitle = "Browse",
            bRoute = "browse",
            icon = R.drawable.ic_browse
        )

    }

    sealed class DrawerScreen(
        val dTitle:String, val dRoute:String, @DrawableRes val icon: Int
    ): Screen(dTitle, dRoute){
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

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route) {
                BottomScreen.Home.bRoute -> BottomScreen.Home
                BottomScreen.Library.bRoute -> BottomScreen.Library
                BottomScreen.Browse.bRoute -> BottomScreen.Browse
                DrawerScreen.Account.dRoute -> DrawerScreen.Account
                DrawerScreen.Subscription.dRoute -> DrawerScreen.Subscription
                DrawerScreen.AddAccount.dRoute -> DrawerScreen.AddAccount
                else -> BottomScreen.Home
            }
        }
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse
)
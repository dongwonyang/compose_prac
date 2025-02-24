package com.example.compose_prac.ui.Screen.main

import com.example.compose_prac.ui.navigation.Screen

data class MainUiState(
    val currentScree: Screen,
    val isDialogOpen: Boolean,
    val showBottomSheet: Boolean
) {
    companion object {
        fun init() = MainUiState(
            currentScree = Screen.DrawerScreen.Account,
            isDialogOpen = false,
            showBottomSheet = false
        )
    }
}
package com.example.compose_prac.ui.Screen.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.compose_prac.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState.init())
    val uiState = _uiState.asStateFlow()


    fun setScreen(screen: Screen) {
        _uiState.update { prev ->
            prev.copy(
                currentScree = screen
            )
        }
    }

    fun setIsDialog(isDialog: Boolean){
        _uiState.update { prev->
            prev.copy(
                isDialogOpen = isDialog
            )
        }
    }

    fun navigateScreen(navController: NavController, screen: Screen){
        _uiState.update { prev->
            prev.copy(
                currentScree = screen
            )
        }
        navController.navigate(screen.route)
    }
}
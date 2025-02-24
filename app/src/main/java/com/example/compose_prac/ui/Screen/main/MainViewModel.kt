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

    fun setIsDialog(isDialog: Boolean){
        _uiState.update { prev->
            prev.copy(
                isDialogOpen = isDialog
            )
        }
    }

    fun setShowBottomSheet(showBottomSheet: Boolean){
        _uiState.update { prev->
            prev.copy(
                showBottomSheet = showBottomSheet
            )
        }
    }

    // 📌 현재 네비게이션 상태를 감지해서 currentScreen을 동기화
    fun updateCurrentScreen(navController: NavController) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        currentRoute?.let { route ->
            val screen = Screen.fromRoute(route) // 🔥 route를 기반으로 Screen을 찾는 함수
            _uiState.update { prev -> prev.copy(currentScree = screen) }
        }
    }

}
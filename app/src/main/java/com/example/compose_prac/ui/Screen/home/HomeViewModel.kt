package com.example.compose_prac.ui.Screen.home

import androidx.lifecycle.ViewModel
import com.example.compose_prac.ui.Screen.main.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState.init())
    val uiState = _uiState.asStateFlow()

}
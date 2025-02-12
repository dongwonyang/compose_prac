package com.example.compose_prac.screen.home

import androidx.lifecycle.ViewModel
import com.example.compose_prac.data.Wish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _wishList = MutableStateFlow<List<Wish>>(emptyList())
    val wishList = _wishList.asStateFlow()
}
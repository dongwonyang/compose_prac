package com.example.compose_prac.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_prac.local.Wish
import com.example.compose_prac.local.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val wishRepository: WishRepository
): ViewModel() {
    private val _wishList = MutableStateFlow<List<Wish>>(emptyList())
    val wishList = _wishList.asStateFlow()

    fun getWishList(): Flow<List<Wish>> = wishRepository.getAllWishes()

    fun deleteWish(wish: Wish) = viewModelScope.launch {
        wishRepository.delete(wish)
    }
}
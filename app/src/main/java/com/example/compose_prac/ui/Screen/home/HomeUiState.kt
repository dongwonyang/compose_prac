package com.example.compose_prac.ui.Screen.home

import com.example.compose_prac.ui.uitls.categoriesList

data class HomeUiState(
    val categories: List<String> = categoriesList,
    val grouped: Map<Char, List<String>> = listOf(
        "New Release",
        "Favorites",
        "Top Rated"
    ).groupBy { it[0] }
) {
    companion object {
        fun init(): HomeUiState =
            HomeUiState()

    }
}

package com.example.compose_prac.ui.navigation

sealed interface Screen {
    val route: String

    object HomeScreen : Screen {
        override val route: String = "home_screen"
    }

    data class AddScreen(
        override val route: String = "add_screen/{id}",
        val id: Long = 0L
    ): Screen {
        fun getFormattedRoute(): String = route.replace("{id}", id.toString())
    }
}
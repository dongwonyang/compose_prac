package com.example.compose_prac.ui.uitls

import androidx.annotation.DrawableRes
import com.example.compose_prac.R

data class Lib(@DrawableRes val icon: Int, val name: String)

val libraries = listOf(
    Lib(icon = R.drawable.ic_launcher_foreground, name = "PlayList"),
    Lib(icon = R.drawable.ic_launcher_foreground, name = "Artists"),
    Lib(icon = R.drawable.ic_launcher_foreground, name = "Album"),
    Lib(icon = R.drawable.ic_launcher_foreground, name = "Songs"),
    Lib(icon = R.drawable.ic_launcher_foreground, name = "Genre"),


    )
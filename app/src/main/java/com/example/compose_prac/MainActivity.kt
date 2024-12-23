package com.example.compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.compose_prac.ui.theme.Compose_pracTheme
import eu.tutorials.myrecipeapp.RecipeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Compose_pracTheme {
                Surface(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
                    RecipeApp(navController = navController)
                }
            }
        }
    }
}

package com.example.compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_prac.ui.theme.Compose_pracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_pracTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen?name={name}"){
        composable("firstScreen?name={name}") {
            val name = it.arguments?.getString("name") ?: ""
            FirstScreen(name) { inputName ->
                navController.navigate("secondScreen?name=$inputName&age=11")
            }
        }

        composable("secondScreen?name={name}&age={age}") {
            val name = it.arguments?.getString("name") ?: ""
            val age = it.arguments?.getString("age") ?: ""
            SecondScreen(name) { inputName ->
                navController.navigate("firstScreen?name=$age")
            }
        }

    }
}
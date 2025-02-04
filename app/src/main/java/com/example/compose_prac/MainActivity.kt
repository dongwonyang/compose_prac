package com.example.compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.compose_prac.ui.theme.Compose_pracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_pracTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    Navigate()
                }
            }
        }
    }
}

@Composable
fun Navigate(){
    val navController = rememberNavController()
    val viewModel: LocationViewModel = viewModel()
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)

    NavHost(navController = navController, startDestination = NavId.ShoppingList.id){
        composable(route = NavId.ShoppingList.id){
            ShoppingListApp(
                locationUtils = locationUtils,
                viewModel = viewModel,
                navController= navController,
                context = context,
                address = viewModel.address.value.firstOrNull() ?: "No Address"
            )
        }

        dialog(route = NavId.Location.id){ backStack ->
            viewModel.location.value?.let{ location ->
                LocationSelectionScreen(location = location, onLocationSelection = { selectedLocation ->
                    viewModel.fetchAddress("${selectedLocation.lat},${selectedLocation.lng}")
                    navController.popBackStack()
                })
            }
        }
    }

}

enum class NavId(val id:String){
    ShoppingList("ShoppingListScreen"), Location("LocationScreen")
}
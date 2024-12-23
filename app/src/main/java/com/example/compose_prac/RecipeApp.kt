package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_prac.CategoryDetailScreen
import com.example.compose_prac.MainViewModel
import com.example.compose_prac.data.Category

@Composable
fun RecipeApp(navController: NavHostController){
    val viewModel: MainViewModel = viewModel()
    val uiState by viewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(uiState = uiState,
                navigateToDetail = { cateogory->
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", cateogory)
                    navController.navigate(Screen.RecipeDetailScreen.route)
                })

        }

        composable(route = Screen.RecipeDetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                ?: Category(idCategory = "", strCategory = "", strCategoryThumb = "", strCategoryDescription = "")

            CategoryDetailScreen(category = category)
        }

    }
}
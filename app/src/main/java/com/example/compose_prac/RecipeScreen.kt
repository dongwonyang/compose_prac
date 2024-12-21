package com.example.compose_prac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.compose_prac.data.Category


@Composable
fun RecipeScreen(modifier: Modifier = Modifier){
    val viewModel: MainViewModel = viewModel()
    val uiState by viewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            uiState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            uiState.error != null ->{
                Text("ERROR OCCURRED")
            }
            else ->{
                CategoryScreen(categories = uiState.list )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>){
    @Composable
    fun CategoryItem(item: Category){
        Column(modifier= Modifier.fillMaxSize().padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(item.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )

            Text(
                text = item.strCategoryDescription,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
                )
        }
    }


    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
                category ->
            CategoryItem(item = category)
        }
    }

}

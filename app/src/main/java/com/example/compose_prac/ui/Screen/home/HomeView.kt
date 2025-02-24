package com.example.compose_prac.ui.Screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_prac.R
import com.example.compose_prac.ui.Screen.browse.BrowserItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val viewModel: HomeViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()

    uiState.value.run {
        LazyColumn{
            grouped.forEach { group ->
                stickyHeader {
                    Text(
                        text = group.value[0],
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyRow {
                        items(categories) { category ->
                            BrowserItem(category = category, drawable = R.drawable.ic_browse)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevHome() {
    HomeView()
}
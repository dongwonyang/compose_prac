package com.example.compose_prac.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    title: String,
    onClickIcon: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onClickIcon()
            }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppbarPrev() {
    Appbar("example")
}
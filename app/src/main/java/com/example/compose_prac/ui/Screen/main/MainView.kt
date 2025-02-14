package com.example.compose_prac.ui.Screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_prac.ui.component.Appbar
import com.example.compose_prac.ui.navigation.Navigation
import com.example.compose_prac.ui.navigation.Screen
import com.example.compose_prac.ui.navigation.screensInDrawer
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val viewModel: MainViewModel = viewModel()
    val mainUiState = viewModel.uiState.collectAsState()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.6f),
                color = Color.White
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(screensInDrawer) { item ->
                        DrawerItem(selected = item.dRoute == currentRoute, item = item) {
                            scope.launch {
                                drawerState.close()
                            }
                            if (item.dRoute == Screen.DrawerScreen.AddAccount.dRoute) {
                                viewModel.setIsDialog(true)
                            } else {
                                viewModel.navigateScreen(navController = navController, screen = item)
                            }
                        }

                    }
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                Appbar(
                    title = mainUiState.value.currentScree.title,
                    onClickIcon = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
            },


            ) { paddingValues ->
            Navigation(navController = navController, viewModel = viewModel, pd = paddingValues)
            AccountDialog(
                dialogOpen = mainUiState.value.isDialogOpen,
                onDismiss = { viewModel.setIsDialog(false) })
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawableItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawableItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier
        )

        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPrev() {
    MainView()
}
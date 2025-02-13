package com.example.compose_prac.ui.screen.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose_prac.component.AppBarView
import com.example.compose_prac.local.Wish

@Composable
fun AddEditDetailView(
    id: Long,
    navController: NavController,
    viewModel: AddEditDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val wishState = viewModel.wish.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val snackMessage = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(id) {
        viewModel.editWish(id)
    }

    LaunchedEffect(snackMessage.value) {
        // 메시지가 있을 경우 스낵바를 표시하도록 처리
        if (snackMessage.value.isNotEmpty()) {
            snackbarHostState.showSnackbar(snackMessage.value)
            snackMessage.value = "" // 메시지 표시 후 다시 초기화
        }
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (wishState.value.id != 0L) "Update Wish" else "Add Wish",
                navController = navController
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // 입력 필드
            WishTextField(label = "Title", value = wishState.value.title, onValueChanged = { value -> viewModel.updateTitle(value) })
            WishTextField(label = "Description", value = wishState.value.description, onValueChanged = { value -> viewModel.updateDescription(value) })

            Spacer(modifier = Modifier.height(10.dp))

            // 버튼 클릭 처리
            Button(onClick = {
                keyboardController?.hide()


                if (viewModel.isNotEmpty()) {
                    viewModel.updateWish(isSuccess = {
                        snackMessage.value = "Success write wish!"
                        navController.navigateUp()
                    })
                } else {
                    snackMessage.value = "Enter fields to create wish!"
                }
            }) {
                Text(
                    text = if (wishState.value.id != 0L) "Update Wish" else "Add Wish",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}



@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged, // 파라미터 이름 수정
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
        )
    )
}

@Preview
@Composable
fun WishTextFieldPrev(){
    WishTextField(label = "text", value = "text") { }
}
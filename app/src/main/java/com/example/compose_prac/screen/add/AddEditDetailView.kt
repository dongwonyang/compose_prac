package com.example.compose_prac.screen.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose_prac.component.AppBarView
import com.example.compose_prac.data.Wish
import com.example.compose_prac.navigation.Screen
import com.example.compose_prac.utils.toastButtonClicked

@Composable
fun AddEditDetailView(
    wish: Wish,
    navController: NavController
) {
    val context = LocalContext.current

    val viewModel: AddEditDetailViewModel = viewModel()
    val wishState = viewModel.wish.collectAsState()
    viewModel.editWish(wish)

    Scaffold(
        topBar = {
            AppBarView(
                title = if (wish.id != 0L) "Update Wish" else "Add Wish",
                navController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title", value = wishState.value.title, onValueChanged = {value -> viewModel.updateTitle(value)})
            WishTextField(label = "Description", value = wishState.value.description, onValueChanged = {value -> viewModel.updateDescription(value)})

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.isNotEmpty()){
                    toastButtonClicked(context)
                    navController.popBackStack()
                    // Todo Update Wish
                }
            }) {
                Text(
                    text = if (wish.id != 0L) "Update Wish" else "Add Wish",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
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
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
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
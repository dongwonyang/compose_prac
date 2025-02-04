package com.example.compose_prac


import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import java.util.UUID

data class ShoppingListItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val isEditing: Boolean = false,
    val address: String = "No Address"
)

data class ShoppingItem(
    val name: String = "",
    val quantity: String = "",
    val address: String = ""
)

@Composable
fun ShoppingListApp(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    navController: NavController,
    context: Context,
    address: String
) {
    var shoppingList by remember {
        mutableStateOf(
            listOf<ShoppingListItem>(
                ShoppingListItem(name = "aa", quantity = 1),
                ShoppingListItem(name = "bb", quantity = 1),
                ShoppingListItem(name = "cc", quantity = 1),
                ShoppingListItem(name = "aa", quantity = 1),
                ShoppingListItem(name = "bb", quantity = 1),
                ShoppingListItem(name = "cc", quantity = 1),
                ShoppingListItem(name = "aa", quantity = 1),
                ShoppingListItem(name = "bb", quantity = 1),
                ShoppingListItem(name = "cc", quantity = 1),
                ShoppingListItem(name = "aa", quantity = 1),
                ShoppingListItem(name = "bb", quantity = 1),
                ShoppingListItem(name = "cc", quantity = 1),
                ShoppingListItem(name = "aa", quantity = 1),
                ShoppingListItem(name = "bb", quantity = 1),
                ShoppingListItem(name = "cc", quantity = 1),

                )
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    val shoppingAddItem = viewModel.shoppingAddItem.collectAsState()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permission ->
            if (permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permission[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                // 권한이 허용된 상태
                Toast.makeText(context, "Location permission granted", Toast.LENGTH_LONG).show()

                locationUtils.requestLocationUpdate(viewModel)
            } else {
                // 권한이 거부된 상태
                val rationaleRequired =
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )

                if (rationaleRequired) {
                    Toast.makeText(
                        context,
                        "Location permission is required for this feature.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Location permission is required. Please enable it in the android settings.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("add item")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(shoppingList) { item ->
                if (item.isEditing) shoppingItemEditor(
                    item = item,
                    onEditComplete = { editedItem ->
                        val itemIndex = shoppingList.indexOfFirst { it.id == item.id }
                        if (itemIndex != -1) {
                            shoppingList = shoppingList.mapIndexed { index, currentItem ->
                                if (index == itemIndex) editedItem else currentItem
                            }
                        }

                        shoppingList = shoppingList.map { it.copy(isEditing = false) }

                    })
                else shoppingListItem(
                    item = item,
                    onEditClick = {
                        shoppingList = shoppingList.map { it.copy(isEditing = it.id == item.id) }
                    },
                    onDeleteClick = {
                        shoppingList = shoppingList - item
                    })

            }

        }
    }


    // Add Dialog
    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            onDismissRequest = {
                viewModel.resetShoppingAddItem()
                showDialog = false
            },
            title = {
                Text(text = "Add Shopping Item")  // 타이틀 설정
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = shoppingAddItem.value.name,
                        onValueChange = { viewModel.updateShoppingItemName(it) },
                        label = { Text("Name") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    OutlinedTextField(
                        value = shoppingAddItem.value.quantity,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                viewModel.updateShoppingItemQuantity(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("Quantity") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    Column {
                        Button(
                            onClick = {
                                if (locationUtils.hasLocationPermission(context)) {
                                    locationUtils.requestLocationUpdate(viewModel = viewModel)
                                    navController.navigate(NavId.Location.id) {
                                        this.launchSingleTop
                                    }
                                } else {
                                    requestPermissionLauncher.launch(
                                        arrayOf(
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION
                                        )
                                    )
                                }
                            }) {
                            Text("Address")
                        }
                        Text(shoppingAddItem.value.address)
                    }
                }

            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (shoppingAddItem.value.name.isNotEmpty() && shoppingAddItem.value.quantity.isNotEmpty()) {
                            shoppingList =
                                shoppingList + ShoppingListItem(
                                    name = shoppingAddItem.value.name,
                                    quantity = shoppingAddItem.value.quantity.toIntOrNull() ?: 0,
                                    address = shoppingAddItem.value.address
                                )
                            viewModel.resetShoppingAddItem()
                            showDialog = false
                        }
                    }) {
                        Text("Add")
                    }

                    Button(onClick = {
                        viewModel.resetShoppingAddItem()
                        showDialog = false
                    }) {
                        Text("Cancel")
                    }

                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = false, // 외부 클릭 시 다이얼로그 닫힘
                usePlatformDefaultWidth = false // 기본 너비 사용 안 함
            )
        )
    }
}


@Composable
fun shoppingListItem(
    item: ShoppingListItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, color = Color(0XFF018786)),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    item.name,
                    modifier = Modifier
                        .weight(1f)  // 여유 공간을 모두 차지
                        .padding(end = 8.dp),
                    maxLines = 1,  // 한 줄로 제한
                    overflow = TextOverflow.Ellipsis  // 넘칠 경우 "..."으로 표시
                )
                Text(item.quantity.toString(), modifier = Modifier.padding(end = 8.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null
                )
                Text(item.address)
            }
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "edit shopping item")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete shopping item"
                )
            }
        }

    }
}

@Composable
fun shoppingItemEditor(
    item: ShoppingListItem,
    onEditComplete: (ShoppingListItem) -> Unit,
) {
    var editedItem by remember { mutableStateOf(item) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {
        Column {
            BasicTextField(
                value = editedItem.name,
                onValueChange = {
                    editedItem = editedItem.copy(name = it)
                },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )

            BasicTextField(
                value = editedItem.quantity.toString(),
                onValueChange = {
                    it.toIntOrNull()?.let {
                        editedItem = editedItem.copy(quantity = it)
                    }
                }, singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }

        Button(
            onClick = {
                onEditComplete(editedItem)
            }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewShoppingListApp() {
}
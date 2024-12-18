package com.example.compose_prac

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_prac.ui.theme.Compose_pracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_pracTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) { _ ->
                    UnitConverter()
                }
            }
        }
    }

    @Composable
    fun UnitConverter(modifier: Modifier = Modifier) {
        val localContext = LocalContext.current
        val textState = remember { mutableStateOf("") }

        val marginBottom10dp = Modifier.padding(bottom = 10.dp)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Unit Converter", modifier = marginBottom10dp)

            Spacer(modifier = Modifier.height(6.dp))
            // TextField 사용
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Enter value") },
                placeholder = { Text("Value") },
                modifier = marginBottom10dp
            )

            // BasicTextField 사용
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .then(marginBottom10dp)
            )

            // OutlinedTextField 사용 (기본 스타일)
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Outlined") },
                modifier = marginBottom10dp
            )

            // OutlinedTextField 사용 (아이콘 추가)
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Customized Outlined") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.Clear, contentDescription = null) },
                modifier = marginBottom10dp
            )

            Spacer(modifier = Modifier.height(6.dp))
            Row {
                Box {
                    Button(onClick = {}) {
                        Text(text = "select")
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDown"
                        )
                    }

                    DropdownMenu(expanded = false, onDismissRequest = {}) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Milimeters") },
                            onClick = {}
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))

                Box {
                    Button(onClick = {}) {
                        Text(text = "select")
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDown"
                        )
                    }

                    DropdownMenu(expanded = false, onDismissRequest = {}) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Milimeters") },
                            onClick = {}
                        )
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun unitConverterPreview() {
        UnitConverter()
    }
}





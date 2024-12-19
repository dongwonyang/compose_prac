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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_prac.ui.theme.Compose_pracTheme
import java.lang.Exception
import kotlin.math.roundToInt

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

//        var inputValue by remember { mutableStateOf("") }
//        var outputValue by remember { mutableStateOf("") }
//
//        var inputUnit by remember { mutableStateOf("Centimeters") }
//        var outputUnit by remember { mutableStateOf("Meters") }
//
//        var inputExpended by remember { mutableStateOf(false) }
//        var outExpended by remember { mutableStateOf(false) }
//
//        val conversionFactors = remember { mutableStateOf<Double>(0.01) }

        val uiState = remember { mutableStateOf<UiState>(UiState()) }

        val marginBottom10dp = Modifier.padding(bottom = 10.dp)

        fun convertUnits() = with(uiState.value) {
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueDouble * converterFactors * 100.0).roundToInt() / 100.0

            uiState.value = uiState.value.copy(outputValue = result.toString())
            Toast.makeText(localContext, uiState.value.outputValue, Toast.LENGTH_LONG).show()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Unit Converter", modifier = marginBottom10dp)

            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                value = uiState.value.inputValue,
                onValueChange = { uiState.value = uiState.value.copy(inputValue = it) },
                label = { Text("TextField") },
                placeholder = { Text("Value") },
                modifier = marginBottom10dp
            )

            BasicTextField(
                value = uiState.value.inputValue,
                onValueChange = { uiState.value = uiState.value.copy(inputValue = it) },
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .then(marginBottom10dp)
            )

            OutlinedTextField(
                value = uiState.value.inputValue,
                onValueChange = { uiState.value = uiState.value.copy(inputValue = it) },
                label = { Text("Outlined") },
                placeholder = { Text("value") },
                modifier = marginBottom10dp
            )

            OutlinedTextField(
                value = uiState.value.inputValue,
                onValueChange = { uiState.value = uiState.value.copy(inputValue = it) },
                label = { Text("Customized Outlined") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.Clear, contentDescription = null) },
                modifier = marginBottom10dp
            )

            Spacer(modifier = Modifier.height(6.dp))
            Row {
                // InputBox
                Box {
                    Button(
                        onClick = { uiState.value = uiState.value.copy(inputExpended = true) }
                    ) {
                        Text(text = uiState.value.inputUnit.toString())
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDown"
                        )
                    }

                    DropdownMenu(
                        expanded = uiState.value.inputExpended,
                        onDismissRequest = {
                            uiState.value = uiState.value.copy(inputExpended = false)
                        }
                    ) {
                        UnitKind.values().forEach {
                            DropdownMenuItem(
                                text = { Text(it.toString()) },
                                onClick = {
                                    uiState.value.let { state ->
                                        uiState.value = uiState.value.copy(
                                            inputExpended = false,
                                            inputUnit = it,
                                            converterFactors = it.unit / state.outputUnit.unit
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))

                // OutputBox
                Box {
                    Button(
                        onClick = { uiState.value = uiState.value.copy(outputExpended = true) }
                    ) {
                        Text(text = uiState.value.outputUnit.toString())
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDown"
                        )
                    }

                    DropdownMenu(
                        expanded = uiState.value.outputExpended,
                        onDismissRequest = {
                            uiState.value = uiState.value.copy(outputExpended = false)
                        }
                    ) {
                        UnitKind.values().forEach {
                            DropdownMenuItem(
                                text = { Text(it.toString()) },
                                onClick = {
                                    uiState.value.let { state ->
                                        uiState.value = uiState.value.copy(
                                            outputExpended = false,
                                            outputUnit = it,
                                            converterFactors = state.inputUnit.unit / it.unit
                                        )
                                    }
                                    convertUnits()
                                }
                            )
                        }
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


enum class UnitKind(val unit: Double) {
    Centimeters(1.0),
    Meters(100.0),
    Feet(30.0),
    Milimeters(0.1)
}


data class UiState(
    val inputValue: String = "",
    val outputValue: String = "",
    val inputUnit: UnitKind = UnitKind.Centimeters,
    val outputUnit: UnitKind = UnitKind.Meters,
    val inputExpended: Boolean = false,
    val outputExpended: Boolean = false,
    val converterFactors: Double = 0.01
)

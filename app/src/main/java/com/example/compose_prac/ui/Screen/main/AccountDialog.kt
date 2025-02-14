package com.example.compose_prac.ui.Screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDialog(dialogOpen: Boolean, onDismiss: () -> Unit) {
    // Declare state variables for email and password
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Function to clear text fields when the dialog is dismissed
    val onDismissWithClearTextField: () -> Unit = {
        onDismiss()
        email = ""  // Clear the email field
        password = ""  // Clear the password field
    }

    if (dialogOpen) {
        BasicAlertDialog(
            onDismissRequest = onDismissWithClearTextField,
            content = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp),
                ) {
                    Text(text = "Add Account?")
                    Spacer(modifier = Modifier.height(16.dp))

                    // TextField for email input
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // TextField for password input
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation() // To hide password input
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // Dismiss button
                        TextButton(onClick = onDismissWithClearTextField) {
                            Text("Dismiss")
                        }

                        // Confirm button
                        TextButton(onClick = {
                            // Handle confirm action here
                            onDismissWithClearTextField() // Close the dialog and clear fields
                        }) {
                            Text("Confirm")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AccountDialogPrev() {
    AccountDialog(true, {})
}
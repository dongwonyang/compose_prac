package com.example.compose_prac.ui.utils

import android.content.Context
import android.widget.Toast

fun toastButtonClicked(context: Context) {
    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
}
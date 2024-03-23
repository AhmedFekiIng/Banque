package com.example.testmobile.theme

import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BankAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(),
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Preview
@Composable
fun PreviewBankAppTheme() {
    BankAppTheme {
        // Preview your UI here
    }
}
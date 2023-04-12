package com.example.baseappcompose.components

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonCommon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
    ) {
        Text(text)
    }
}
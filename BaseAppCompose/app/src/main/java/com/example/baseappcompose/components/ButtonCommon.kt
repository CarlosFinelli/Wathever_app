package com.example.baseappcompose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCommon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    icon: Int? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
    ) {
        if(icon != null) {
            Icon(painterResource(id = icon),  contentDescription = null, Modifier.padding(end = 8.dp))
        }
        Text(text)
    }
}
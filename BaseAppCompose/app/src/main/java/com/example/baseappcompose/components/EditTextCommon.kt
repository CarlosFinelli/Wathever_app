package com.example.baseappcompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EditTextCommon(
    modifier: Modifier = Modifier,
    valor: MutableState<String>,
    placeholder: String = "",
    hint: String
) {
    OutlinedTextField(
        value = valor.value,
        onValueChange = { valor.value = it },
        modifier = modifier,
        label = { Text(hint) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}
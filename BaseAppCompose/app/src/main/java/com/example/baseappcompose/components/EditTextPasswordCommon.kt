package com.example.baseappcompose.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun EditTextPasswordCommon(
    modifier: Modifier = Modifier,
    valor: MutableState<String>,
    placeholder: String = "",
    hint: String
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = valor.value,
        onValueChange = { valor.value = it },
        modifier = modifier,
        label = { Text(hint) },
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if(passwordVisible) {
                com.example.baseappcompose.R.drawable.baseline_visibility_off
            } else com.example.baseappcompose.R.drawable.baseline_visibility

            val description = if(passwordVisible) "Hide" else "Unhide"

            IconButton(onClick = { passwordVisible = !passwordVisible}) {
                Icon(painterResource(image), description)
            }
        }
    )
}
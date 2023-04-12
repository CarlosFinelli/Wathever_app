package com.example.baseappcompose.views.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.baseappcompose.Destinations.LOGIN
import com.example.baseappcompose.components.ButtonCommon
import com.example.baseappcompose.components.EditTextCommon
import com.example.baseappcompose.components.EditTextPasswordCommon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    val nome = remember { mutableStateOf("") }
    val usuario = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Criar acesso",
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(24.dp))
            EditTextCommon(
                valor = nome,
                hint = "Nome",
                placeholder = "Insira o seu nome completo"
            )
            Spacer(Modifier.height(8.dp))
            EditTextCommon(
                valor = usuario,
                hint = "Usuário",
                placeholder = "Insira o seu usuário"
            )
            Spacer(Modifier.height(8.dp))
            EditTextPasswordCommon(
                valor = senha,
                hint = "Senha",
                placeholder = "Insira a sua senha"
            )
            Spacer(Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(100.dp, 250.dp)
            ) {
                ButtonCommon(
                    onClick = { navController.navigate(LOGIN) },
                    text = "Registrar",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    Modifier
                        .padding(end = 6.dp)
                        .width(178.dp)
                )
                Text("Ou")
                Divider(
                    Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(100.dp, 250.dp)
            ) {
                ButtonCommon(
                    onClick = { navController.popBackStack(LOGIN, false) },
                    text = "Voltar",
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                )
            }
        }
    }
}
package com.example.baseappcompose.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.baseappcompose.Destinations.MAIN
import com.example.baseappcompose.Destinations.REGISTER
import com.example.baseappcompose.components.ButtonCommon
import com.example.baseappcompose.components.EditTextCommon
import com.example.baseappcompose.components.EditTextPasswordCommon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()
    val login = remember {
        mutableStateOf("")
    }
    val senha = remember {
        mutableStateOf("")
    }
    Scaffold(scaffoldState = scaffoldState, modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            EditTextCommon(
                valor = login,
                hint = "Usuário",
                placeholder = "Informe o seu usuário"
            )
            Spacer(modifier = Modifier.height(8.dp))
            EditTextPasswordCommon(
                valor = senha,
                hint = "Senha",
                placeholder = "Informe a sua senha"
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextButton(
//                "Esqueci minha senha",
//                color = MaterialTheme.colors.primary,
//                modifier = Modifier.width(280.dp),
//                textAlign = TextAlign.End,
//                fontSize = 16.sp,
                onClick = { TODO() }
            ) {
                Text(
                    "Esqueci minha senha",
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.width(280.dp),
                    textAlign = TextAlign.End,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(100.dp, 250.dp)
            ) {
                ButtonCommon(
                    onClick = { navController.navigate(MAIN) },
                    text = "Entrar",
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    Modifier.padding(end = 6.dp).width(178.dp)
                )
                Text("Ou")
                Divider(
                    Modifier.padding(start = 6.dp).fillMaxWidth()
                )
            }
            Spacer(Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(100.dp, 250.dp)
            ) {
                ButtonCommon(
                    onClick = { navController.navigate(REGISTER) },
                    text = "Registrar",
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.background)
                )
            }
        }
    }
}
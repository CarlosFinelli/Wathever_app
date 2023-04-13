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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.baseappcompose.Destinations.MAIN
import com.example.baseappcompose.Destinations.REGISTER
import com.example.baseappcompose.R
import com.example.baseappcompose.components.ButtonCommon
import com.example.baseappcompose.components.EditTextCommon
import com.example.baseappcompose.components.EditTextPasswordCommon
import com.example.baseappcompose.views.login.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel = viewModel(LoginViewModel::class.java)

    val scaffoldState = rememberScaffoldState()
    val login = remember {
        mutableStateOf("")
    }
    val senha = remember {
        mutableStateOf("")
    }

    if (viewModel.isSnackBarShowing) {
        ShowSnackBar(scaffoldState = scaffoldState, viewModel)
    }

    if(viewModel.showNextScreen != null && !viewModel.showNextScreen!!) ShowSnackLogin(scaffoldState, viewModel)
    else if (viewModel.showNextScreen != null && viewModel.showNextScreen!!) navController.navigate(MAIN)

    Scaffold(scaffoldState = scaffoldState, modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
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
                    //onClick = { navController.navigate(MAIN) },
                    onClick = {
                              if(viewModel.isSnackBarShowing) {
                                  viewModel.hideSnackBar()
                              } else {
                                  viewModel.showSnackBar()
                              }
                    },
                    text = "Entrar",
                    modifier = Modifier.fillMaxWidth(),
                    icon = R.drawable.baseline_login
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
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.background),
                    icon = R.drawable.baseline_app_registration
                )
            }
        }
    }
}

@Composable
fun ShowSnackBar(scaffoldState: ScaffoldState, viewModel: LoginViewModel) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message =  viewModel.generateSHA("Teste SHA"),
            duration = SnackbarDuration.Short,
            actionLabel = "Teste SHA"
        )
    }
}

@Composable
fun ShowSnackLogin(scaffoldState: ScaffoldState, viewModel: LoginViewModel) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = "Usuário ou senha inválidos!!",
            duration = SnackbarDuration.Short
        )
    }
}
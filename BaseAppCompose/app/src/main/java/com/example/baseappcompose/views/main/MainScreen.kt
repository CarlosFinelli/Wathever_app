package com.example.baseappcompose.views.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.baseappcompose.BottomNavigationBar
import com.example.baseappcompose.Destinations
import com.example.baseappcompose.Destinations.LOGIN
import com.example.baseappcompose.classes.BottomNavItem
import com.example.baseappcompose.components.ButtonCommon
import com.example.baseappcompose.ui.theme.Shapes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
            //.padding(16.dp),
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Destinations.MAIN,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Galeria",
                        route = Destinations.GALLERY,
                        icon = Icons.Default.ThumbUp
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Módulos disponíveis:",
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier.weight(1f)
            ) {
                itemsIndexed(viewModel.defaultLoad().toList())  { index, item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 3.dp,
                        shape =  Shapes.medium
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .height(IntrinsicSize.Min)
                        ) {
                            Text("#${item.id}", modifier = Modifier.padding(end = 6.dp))
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight()
                            )
                            Text(item.modulo, modifier = Modifier.padding(start = 6.dp))
                            Spacer(Modifier.height(16.dp))
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.widthIn(100.dp, 250.dp)
            ) {
                ButtonCommon(
                    onClick = { navController.popBackStack(LOGIN, false) },
                    text = "Sair",
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
package com.example.baseappcompose.views.gallery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.baseappcompose.BottomNavigationBar
import com.example.baseappcompose.Destinations.GALLERY
import com.example.baseappcompose.Destinations.MAIN
import com.example.baseappcompose.R
import com.example.baseappcompose.classes.BottomNavItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GalleryScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    Box(
        //scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            BottomNavigationBar(
//                items = listOf(
//                    BottomNavItem(
//                        name = "Home",
//                        route = MAIN,
//                        icon = Icons.Default.Home
//                    ),
//                    BottomNavItem(
//                        name = "Galeria",
//                        route = GALLERY,
//                        icon = Icons.Default.ThumbUp
//                    )
//                ),
//                navController = navController,
//                onItemClick = {
//                    navController.navigate(it.route)
//                }
//            )
//        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp)
        ) {

        }
    }
}
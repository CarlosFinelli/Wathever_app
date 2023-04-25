package com.example.baseappcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baseappcompose.Destinations.GALLERY
import com.example.baseappcompose.Destinations.LOGIN
import com.example.baseappcompose.Destinations.MAIN
import com.example.baseappcompose.Destinations.REGISTER
import com.example.baseappcompose.views.LoginScreen
import com.example.baseappcompose.views.gallery.GalleryScreen
import com.example.baseappcompose.views.main.MainScreen
import com.example.baseappcompose.views.register.RegisterScreen

object Destinations {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val MAIN = "main"
    const val GALLERY = "gallery"
}

@Composable
fun BaseAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, LOGIN) {

        composable(LOGIN) {
            LoginScreen(navController)
        }

        composable(REGISTER) {
            RegisterScreen(navController = navController)
        }
        
        composable(MAIN) {
            MainScreen(navController = navController)
        }

        composable(GALLERY) {
            GalleryScreen(navController = navController)
        }
    }
}
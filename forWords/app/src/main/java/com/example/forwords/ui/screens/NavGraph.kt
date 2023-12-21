package com.example.forwords.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "book_screen") {
        composable("login_screen") {
            LoginScreen()
        }

        composable("user_screen") {
            UserScreen()
        }

        composable("book_screen") {
            BookScreen()
        }



    }
}
package com.example.forwords.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.forwords.data.BookModel
import com.example.forwords.util.NavigationRoutes

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route
    ) {
        unauthenticatedGraph(navController = navHostController)

        // Authenticated user flow screens
        authenticatedGraph(navController = navHostController)
    }
}

fun NavGraphBuilder.unauthenticatedGraph(navController: NavHostController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
            LoginScreen(
                onNavigateToRegistration = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Registration.route)
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Registration
        composable(route = NavigationRoutes.Unauthenticated.Registration.route) {
            RegistrationScreen(onNavigateToAuthenticatedRoute = {
                navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                    popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                        inclusive = true
                    }
                }
            })
        }
    }
}

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Home.route
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Home.route) {
            HomeScreen()
        }

        composable(route = NavigationRoutes.Authenticated.User.route) {
            UserScreen()
        }

        composable(route = NavigationRoutes.Authenticated.Book.route) {
                BookScreen()
        }
    }
}
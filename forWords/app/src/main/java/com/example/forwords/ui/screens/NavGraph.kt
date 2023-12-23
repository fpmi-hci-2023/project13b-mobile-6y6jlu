package com.example.forwords.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.forwords.data.BookModel
import com.example.forwords.util.NavigationRoutes

@Composable
fun NavGraph(navHostController: NavHostController, context: Context) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route
    ) {
        unauthenticatedGraph(navController = navHostController, context)

        authenticatedGraph(navController = navHostController, context)
    }
}

fun NavGraphBuilder.unauthenticatedGraph(navController: NavHostController, context: Context) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

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
                },
                context
            )
        }

        composable(route = NavigationRoutes.Unauthenticated.Registration.route) {
            RegistrationScreen(onNavigateToAuthenticatedRoute = {
                navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                    popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                        inclusive = true
                    }
                }
            }, context)
        }
    }
}

fun NavGraphBuilder.authenticatedGraph(navController: NavHostController, context: Context) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Home.route
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Home.route) {
            HomeScreen(onNavigateToBook = { book_id ->
                navController.navigate(
                    route = NavigationRoutes.Authenticated.Book.getFullRoute(
                        book_id
                    )
                ) {
                    popUpTo(route = NavigationRoutes.Authenticated.Home.route) {
                    }
                }
            })
        }

        composable(route = NavigationRoutes.Authenticated.User.route) {
            UserScreen(
                onNavigateToBook = { book_id ->
                    navController.navigate(
                        route = NavigationRoutes.Authenticated.Book.getFullRoute(
                            book_id
                        )
                    ) {
                        popUpTo(route = NavigationRoutes.Authenticated.Home.route) {
                        }
                    }
                },
                context
            )
        }

        composable(
            route = NavigationRoutes.Authenticated.Book.route,
            arguments = listOf(
                navArgument(name = "book_id") {
                    type = NavType.IntType
                })
        ) { navBackStackEntry ->
            val book_id = navBackStackEntry.arguments?.getInt("book_id")
            if (book_id != null) {
                BookScreen(book_id = book_id)
            }
        }
    }
}
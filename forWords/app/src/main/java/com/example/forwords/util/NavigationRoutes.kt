package com.example.forwords.util

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Unauthenticated(route = "unauthenticated")
        object Login : Unauthenticated(route = "login")
        object Registration : Unauthenticated(route = "registration")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Authenticated(route = "authenticated")
        object Home : Authenticated(route = "home")
        object User : Authenticated(route = "user")
        object Book: Authenticated(route = "book" + "/{book_id}") {
            fun getFullRoute(book_id: Int): String {
                return "book" + "/$book_id"
            }
        }

    }
}
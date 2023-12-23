package com.example.forwords.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.forwords.R
import com.example.forwords.util.NavigationRoutes
import com.example.testapp.ui.theme.LightBlue


@Composable
fun DrawerHeader(navController: NavHostController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navController.navigate(route = NavigationRoutes.Authenticated.Home.route)
                }
        )
    }
}

@Composable
fun DrawerBody(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
    ) {
        items(7) {
            when (it) {
                0 -> Text("My profile", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(route = NavigationRoutes.Authenticated.User.route) {
                            popUpTo(route = NavigationRoutes.Authenticated.Home.route) {
                            }
                        }
                    }
                    .padding(10.dp))

                1 -> Text("Reviews", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("") }
                    .padding(10.dp))

                2 -> Text("Challenge", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("") }
                    .padding(10.dp))

                3 -> Text("Friends", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("") }
                    .padding(10.dp))

                4 -> Text("Collections", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("") }
                    .padding(10.dp))

                5 -> Text("Settings", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("") }
                    .padding(10.dp))

                6 -> Text("Log out", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(route = NavigationRoutes.Unauthenticated.Login.route) }
                    .padding(10.dp))
            }
        }
    }
}
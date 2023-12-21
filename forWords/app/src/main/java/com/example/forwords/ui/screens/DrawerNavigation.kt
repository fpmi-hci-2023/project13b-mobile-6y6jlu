package com.example.forwords.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testapp.ui.theme.LightBlue


@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(70.dp)
            .background(LightBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "There will be logo", fontSize = 30.sp, color = Color.White)
    }
}

@Composable
fun DrawerBody(navController: NavHostController) {
    LazyColumn(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))) {
        items(7) {
            when (it) {
                0 -> Text("My profile", modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("user_screen") }
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
                    .clickable { navController.navigate("login_screen") }
                    .padding(10.dp))
            }
        }
    }
}
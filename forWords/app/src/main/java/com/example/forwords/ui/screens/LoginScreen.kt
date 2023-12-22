package com.example.forwords.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forwords.R
import com.example.forwords.data.LoginCredentials
import com.example.forwords.network.ApiManager
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val apiService = ApiManager()
    val coroutineScope = rememberCoroutineScope()

    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background_image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds
    )
    Scaffold(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .alpha(0.5f)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Join us",
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text("Login") },
                value = login.value,
                onValueChange = {
                    login.value = it
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text("Password") },
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                val login_cred = LoginCredentials(login.value, password.value)
                apiService.signIn(login_cred) {
                    if(it != null) {
                        onNavigateToAuthenticatedRoute.invoke()
                    }
                }
            }) {
                Text(text = "Log in")
            }

            Spacer(modifier = Modifier.height(200.dp))
            Text(
                text = "tip: don’t read books in moving vehicles",
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                )
            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Register")
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onNavigateToRegistration.invoke() },
                textAlign = TextAlign.Center,
            )
        }
    }
}
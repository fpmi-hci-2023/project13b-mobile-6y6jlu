package com.example.forwords.data

data class LoginCredentials(
    val login: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val userId: Int
)

data class RegisterCredentials(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val info: String
)
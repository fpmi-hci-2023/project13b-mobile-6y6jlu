package com.example.forwords.network

import com.example.forwords.data.LoginCredentials
import com.example.forwords.data.LoginResponse
import com.example.forwords.data.RegisterCredentials
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IConnectAPI {
    @POST("/api/v1/books/signIn")
    fun signIn(@Body credentials: LoginCredentials): Call<LoginResponse>

    @POST("/api/v1/books/registration")
    fun register(@Body credentials: RegisterCredentials): Call<LoginResponse>
}
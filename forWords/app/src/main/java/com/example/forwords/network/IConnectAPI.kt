package com.example.forwords.network

import com.example.forwords.data.BookModel
import com.example.forwords.data.FullBookModel
import com.example.forwords.data.LoginCredentials
import com.example.forwords.data.LoginResponse
import com.example.forwords.data.RegisterCredentials
import com.example.forwords.data.UserBookModel
import com.example.forwords.data.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IConnectAPI {
    @POST("/api/v1/books/signIn")
    fun signIn(@Body credentials: LoginCredentials): Call<LoginResponse>

    @POST("/api/v1/books/registration")
    fun register(@Body credentials: RegisterCredentials): Call<LoginResponse>

    @POST("/api/v1/books/search/title")
    fun getBookByName(@Body name: String): Call<List<BookModel>>

    @POST("/api/v1/books/all")
    fun getAllBooks(): Call<List<BookModel>>

    @POST("/api/v1/books/search/id")
    fun getBookById(@Body book_id: Int): Call<FullBookModel>

    @POST("/api/v1/books/user")
    fun getUserById(@Body user_id: Int): Call<UserModel>


    @POST("/api/v1/books/get_user_books")
    fun getUserBooks(@Body user_id: Int): Call<List<UserBookModel>>
}
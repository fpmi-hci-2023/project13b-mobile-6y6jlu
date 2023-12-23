package com.example.forwords.network

import com.example.forwords.data.BookModel
import com.example.forwords.data.FullBookModel
import com.example.forwords.data.LoginCredentials
import com.example.forwords.data.LoginResponse
import com.example.forwords.data.RegisterCredentials
import com.example.forwords.data.UserBookModel
import com.example.forwords.data.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager {
    fun signIn(login_cred: LoginCredentials, onResult: (LoginResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.signIn(login_cred).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun register(register_cred: RegisterCredentials, onResult: (LoginResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.register(register_cred).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun getBookByName(name: String, onResult: (List<BookModel>?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.getBookByName(name).enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun getBookById(book_id: Int, onResult: (FullBookModel?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.getBookById(book_id).enqueue(
            object : Callback<FullBookModel> {
                override fun onResponse(
                    call: Call<FullBookModel>,
                    response: Response<FullBookModel>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<FullBookModel>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun getAllBooks(onResult: (List<BookModel>?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.getAllBooks().enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }


    fun getUserById(user_id: Int, onResult: (UserModel?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.getUserById(user_id).enqueue(
            object : Callback<UserModel> {
                override fun onResponse(
                    call: Call<UserModel>,
                    response: Response<UserModel>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun getUserBooks(user_id: Int, onResult: (List<UserBookModel>?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(IConnectAPI::class.java)
        retrofit.getUserBooks(user_id).enqueue(
            object : Callback<List<UserBookModel>> {
                override fun onResponse(
                    call: Call<List<UserBookModel>>,
                    response: Response<List<UserBookModel>>
                ) {
                    var result = response.body()
                    onResult(result)
                }

                override fun onFailure(call: Call<List<UserBookModel>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}
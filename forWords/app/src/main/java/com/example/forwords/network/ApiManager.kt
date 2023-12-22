package com.example.forwords.network

import com.example.forwords.data.LoginCredentials
import com.example.forwords.data.LoginResponse
import com.example.forwords.data.RegisterCredentials
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
}
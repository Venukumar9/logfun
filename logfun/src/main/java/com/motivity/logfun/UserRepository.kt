package com.motivity.logfun

import com.motivity.logfun.Api
import com.motivity.logfun.ApiResponse
import com.motivity.logfun.LoginResponse
import com.motivity.logfun.UserLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api: Api) {

    fun userLogin(email: String, password: String, callback: (ApiResponse<LoginResponse>) -> Unit) {
        api.userLogin(UserLogin(email, password), "application/json").enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        callback(ApiResponse.Success(loginResponse))
                    } else {
                        // Handle the case when response.body() is null
                        callback(ApiResponse.Error( response.code(),"something went wrong" ))
                    }
                } else {
                    callback(ApiResponse.Error(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(ApiResponse.Error(-1, t.message ?: "Unknown error"))
            }
        })
    }

    // ...
}
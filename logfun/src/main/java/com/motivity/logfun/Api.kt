package com.motivity.logfun

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {

    @POST("login")
    fun userLogin(
        @Body userLogin: UserLogin,
        @Header("Content-Type") contentType: String
    ): Call<LoginResponse>
}
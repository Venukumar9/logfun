package com.motivity.logfun

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "http://172.20.100.13:7071/api/user/" // Update the base URL to match your API endpoint

    private val okHttpClient = OkHttpClient.Builder()
        // .connectTimeout(40,TimeUnit.SECONDS)
        // .readTimeout(40,TimeUnit.SECONDS)
        .addInterceptor { chain ->
            var original = chain.request()
            var requestBuilder = original.newBuilder()
                .method(
                    original.method(), original.body()
                )
                .header("Content-Type", "Application/json")
            var request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()
    var gson = GsonBuilder()
        .setLenient()
        .create()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //      .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}


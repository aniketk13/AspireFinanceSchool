package com.aspire.aspirefinanceschool.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://bravo.castor.letsaspire.in/"

object RetrofitInstance {
    //logging interceptor for better log results
    var loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(
        loggingInterceptor
    )

    //private instance
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //public instance
    val api: AspireFinanceAPI by lazy {
        retrofit.create(AspireFinanceAPI::class.java)
    }
}
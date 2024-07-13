package com.example.loginproject.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit : Retrofit by lazy {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        Retrofit.Builder().apply {

            baseUrl("https://apolisrises.co.in/myshop/index.php/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)

        }.build()

    }
}
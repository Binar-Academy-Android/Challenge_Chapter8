package com.example.challenge_chapter6_fix.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiClient {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"

        val logging: HttpLoggingInterceptor
            get() {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                return httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}
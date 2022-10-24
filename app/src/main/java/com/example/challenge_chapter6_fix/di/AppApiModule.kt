package com.example.challenge_chapter6_fix.di

import com.example.challenge_chapter6_fix.service.ApiClient
import com.example.challenge_chapter6_fix.service.ApiClient.Companion.BASE_URL
import com.example.challenge_chapter6_fix.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppApiModule {

    @Singleton
    @Provides
    fun clientRetrofit(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiClient.logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}
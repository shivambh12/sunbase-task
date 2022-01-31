package com.example.sunbase_task.di

import android.app.Application
import androidx.room.Room
import com.example.sunbase_task.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit =
        Retrofit.Builder().baseUrl("https://api.unsplash.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
            .build()
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):ApiInterface = retrofit.create(ApiInterface::class.java)
}
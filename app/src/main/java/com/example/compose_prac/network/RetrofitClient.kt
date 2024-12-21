package com.example.compose_prac.network

import com.example.compose_prac.data.RecipeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val recipeOkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val recipeRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(recipeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val recipeService by lazy {
        recipeRetrofit.create(RecipeApiService::class.java)
    }
}
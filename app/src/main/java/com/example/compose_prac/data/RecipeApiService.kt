package com.example.compose_prac.data

import retrofit2.http.GET

interface RecipeApiService{
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}
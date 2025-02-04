package com.example.compose_prac.data

import com.example.compose_prac.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApiService {
    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY
    ): GeocodeResponse
}
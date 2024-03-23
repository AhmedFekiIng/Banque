package com.example.data

import com.example.data.Bank
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("banks.json")
    suspend fun getBanks(): List<Bank>
}

private const val BASE_URL = "https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/"

fun createApiService(): com.example.data.ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(com.example.data.ApiService::class.java)
}
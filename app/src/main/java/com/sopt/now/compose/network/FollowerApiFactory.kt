package com.sopt.now.compose.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object FollowerApiFactory {
    private const val BASE_URL: String = "https://reqres.in/api/"

    private val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}
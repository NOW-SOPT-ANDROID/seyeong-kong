package com.sopt.now.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowerApiFactory @Inject constructor(
    val retrofit: Retrofit
) {
    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}
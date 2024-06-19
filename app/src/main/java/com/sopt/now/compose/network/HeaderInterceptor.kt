package com.sopt.now.compose.network

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val memberId = sharedPreferences.getString("MemberID", "default_member_id") ?: "default_member_id"
        val newRequest = originalRequest.newBuilder()
            .addHeader("memberId", memberId)
            .build()
        return chain.proceed(newRequest)
    }
}
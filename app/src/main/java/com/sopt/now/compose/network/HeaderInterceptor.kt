package com.sopt.now.compose.network

import android.content.Context
import com.sopt.now.compose.SoptApp
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val app = context.applicationContext as SoptApp
        val memberId = app.serviceLocator.userRepository.getMemberId() ?: "default_member_id"
        val newRequest = originalRequest.newBuilder()
            .addHeader("memberId", memberId)
            .build()
        return chain.proceed(newRequest)
    }
}
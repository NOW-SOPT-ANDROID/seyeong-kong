package com.sopt.now.compose.network

import com.sopt.now.compose.data.UserRepository
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val userRepository: UserRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val memberId = userRepository.getMemberId() ?: "default_member_id"
        val newRequest = originalRequest.newBuilder()
            .addHeader("memberId", memberId)
            .build()
        return chain.proceed(newRequest)
    }
}
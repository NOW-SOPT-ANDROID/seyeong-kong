package com.sopt.now.network

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import com.sopt.now.SoptApp
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
        val response = chain.proceed(newRequest)

        if (response.code == ERROR_USER_NOT_FOUND) {
            ProcessPhoenix.triggerRebirth(context)
        }
        return response
    }

    companion object {
        private const val ERROR_USER_NOT_FOUND = 404
    }
}
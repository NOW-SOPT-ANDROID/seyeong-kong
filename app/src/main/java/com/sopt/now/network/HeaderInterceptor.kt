package com.sopt.now.network

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.processphoenix.ProcessPhoenix
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val memberId = sharedPreferences.getString("MemberID", "default_member_id") ?: "default_member_id"
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
package com.sopt.now.network.service

import com.sopt.now.network.response.ResponseFollowerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("users")
    suspend fun getFollowers(
        @Query("page") page: Int,
    ): Response<ResponseFollowerDto>
}
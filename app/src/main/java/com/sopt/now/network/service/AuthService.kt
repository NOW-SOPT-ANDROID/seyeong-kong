package com.sopt.now.network.service

import com.sopt.now.network.request.RequestChangePasswordDto
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.response.ResponseInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<ResponseDto>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseDto>

    @PATCH("member/password")
    suspend fun changePassword(
        @Body request: RequestChangePasswordDto,
    ): Response<ResponseDto>

    @GET("member/info")
    suspend fun info(): Response<ResponseInfoDto>
}
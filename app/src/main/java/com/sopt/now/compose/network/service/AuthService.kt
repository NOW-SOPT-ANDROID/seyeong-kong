package com.sopt.now.compose.network.service

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
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
    suspend fun userInfo(): Response<ResponseInfoDto>
}
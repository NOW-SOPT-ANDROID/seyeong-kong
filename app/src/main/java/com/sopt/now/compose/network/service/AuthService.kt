package com.sopt.now.compose.network.service

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.request.RequestChPwDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseDto>

    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseDto>

    @PATCH("member/password")
    fun chPassword(
        @Body request: RequestChPwDto,
    ): Call<ResponseDto>

    @GET("member/info")
    fun info(): Call<ResponseInfoDto>
}
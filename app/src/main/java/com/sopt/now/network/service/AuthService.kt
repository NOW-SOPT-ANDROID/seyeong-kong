package com.sopt.now.network.service

import com.sopt.now.network.request.RequestChangePwDto
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.response.ResponseInfoDto
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
        @Body request: RequestChangePwDto,
    ): Call<ResponseDto>

    @GET("member/info")
    fun info(): Call<ResponseInfoDto>
}
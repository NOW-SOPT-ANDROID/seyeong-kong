package com.sopt.now.network

import retrofit2.Call
import retrofit2.http.Body
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
}
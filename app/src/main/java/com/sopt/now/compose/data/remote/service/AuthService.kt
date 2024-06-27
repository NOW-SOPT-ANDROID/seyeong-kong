package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.request.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUp(
        @Body user: RequestUserEntity,
    ): Response<BaseResponseWithoutDataDto>

    @POST("member/login")
    suspend fun postSignIn(
        @Body user: RequestSignInEntity,
    ): Response<BaseResponseWithoutDataDto>

    @PATCH("member/password")
    suspend fun postChangePassword(
        @Body request: RequestChangePasswordEntity,
    ): Response<BaseResponseWithoutDataDto>
}
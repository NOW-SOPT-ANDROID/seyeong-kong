package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.RequestSignInEntity
import com.sopt.now.compose.domain.entity.RequestUserEntity
import retrofit2.Response

interface AuthDataSource {
    suspend fun postSignUp(user: RequestUserEntity): Response<BaseResponseWithoutDataDto>

    suspend fun postSignIn(user: RequestSignInEntity): Response<BaseResponseWithoutDataDto>

    suspend fun postChangePassword(user: RequestChangePasswordEntity): Response<BaseResponseWithoutDataDto>
}
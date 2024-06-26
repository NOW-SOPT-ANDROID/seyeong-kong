package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.RequestSignInEntity
import com.sopt.now.compose.domain.entity.RequestUserEntity
import retrofit2.Response

interface AuthRepository {
    suspend fun signup(user: RequestUserEntity): Result<Response<BaseResponseWithoutDataDto>>
    suspend fun login(user: RequestSignInEntity): Result<Response<BaseResponseWithoutDataDto>>
    suspend fun changePassword(user: RequestChangePasswordEntity): Result<Response<BaseResponseWithoutDataDto>>
    fun getId(): String
    fun getPassword(): String
    fun getNickname(): String
    fun getPhoneNumber(): String
    fun setId(id: String)
    fun setPassword(password: String)
    fun setNickname(nickname: String)
    fun setPhoneNumber(phoneNumber: String)
    fun setUserId(userId: String)
    fun setUserLoggedIn(loggedIn: Boolean)
    fun clearInfo()
}

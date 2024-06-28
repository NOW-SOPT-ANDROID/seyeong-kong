package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.request.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.entity.Result

interface AuthRepository {
    suspend fun signup(user: RequestUserEntity): Result<Unit>
    suspend fun login(user: RequestSignInEntity): Result<Unit>
    suspend fun changePassword(user: RequestChangePasswordEntity): Result<Unit>
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

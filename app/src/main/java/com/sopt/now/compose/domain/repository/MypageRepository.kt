package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.response.ResponseUserInfo

interface MypageRepository {
    suspend fun getUserInfo(): Result<ResponseUserInfo>
}
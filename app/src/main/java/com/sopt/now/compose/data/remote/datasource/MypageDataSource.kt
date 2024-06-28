package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.response.BaseResponseDto
import com.sopt.now.compose.data.remote.response.ResponseUserDto

interface MypageDataSource {
    suspend fun getUserInfo(): BaseResponseDto<ResponseUserDto>
}
package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.response.BaseResponseDto
import com.sopt.now.compose.data.remote.response.ResponseUserDto
import retrofit2.http.GET

interface MypageService {
    @GET("member/info")
    suspend fun getUserInfo():BaseResponseDto<ResponseUserDto>
}
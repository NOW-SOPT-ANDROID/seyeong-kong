package com.sopt.now.compose.data.remote.datasourceImpl

import com.sopt.now.compose.data.remote.datasource.MypageDataSource
import com.sopt.now.compose.data.remote.response.BaseResponseDto
import com.sopt.now.compose.data.remote.response.ResponseUserDto
import com.sopt.now.compose.data.remote.service.MypageService
import javax.inject.Inject

class MypageDataSourceImpl @Inject constructor(
    private val maypageService: MypageService,
) : MypageDataSource {
    override suspend fun getUserInfo(): BaseResponseDto<ResponseUserDto> = maypageService.getUserInfo()
}
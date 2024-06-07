package com.sopt.now.data

import com.sopt.now.network.request.RequestChangePasswordDto
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.response.ResponseInfoDto
import com.sopt.now.network.service.AuthService
import retrofit2.Response

class UserRemoteDataSource(private val authService: AuthService) {
    suspend fun signUp(request: RequestSignUpDto): Response<ResponseDto> {
        return authService.signUp(request)
    }

    suspend fun login(request: RequestLoginDto): Response<ResponseDto> {
        return authService.login(request)
    }

    suspend fun getUserInfo(): Response<ResponseInfoDto> {
        return authService.info()
    }

    suspend fun changePassword(request: RequestChangePasswordDto): Response<ResponseDto> {
        return authService.changePassword(request)
    }
}
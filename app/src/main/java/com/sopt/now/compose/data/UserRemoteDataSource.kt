package com.sopt.now.compose.data

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.service.AuthService
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
import retrofit2.Response

class UserRemoteDataSource(private val authService: AuthService) {
    suspend fun signUp(request: RequestSignUpDto): Response<ResponseDto> {
        return authService.signUp(request)
    }

    suspend fun login(request: RequestLoginDto): Response<ResponseDto> {
        return authService.login(request)
    }

    suspend fun getUserInfo(): Response<ResponseInfoDto> {
        return authService.userInfo()
    }

    suspend fun changePassword(request: RequestChangePasswordDto): Response<ResponseDto> {
        return authService.changePassword(request)
    }
}
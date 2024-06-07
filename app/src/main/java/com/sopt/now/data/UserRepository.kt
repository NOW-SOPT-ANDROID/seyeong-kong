package com.sopt.now.data

import com.sopt.now.network.request.RequestChangePasswordDto
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.response.ResponseInfoDto
import retrofit2.Response

interface UserRepository {
    fun setUserLoggedIn(loggedIn: Boolean)
    fun isUserLoggedIn(): Boolean
    fun logoutUser()
    fun saveUserData(user: User)
    fun updateUserPassword(newPassword: String)
    fun setMemberId(memberId: String)
    suspend fun signUp(request: RequestSignUpDto): Response<ResponseDto>
    suspend fun login(request: RequestLoginDto): Response<ResponseDto>
    suspend fun getUserInfo(): Response<ResponseInfoDto>
    suspend fun changePassword(request: RequestChangePasswordDto): Response<ResponseDto>
}
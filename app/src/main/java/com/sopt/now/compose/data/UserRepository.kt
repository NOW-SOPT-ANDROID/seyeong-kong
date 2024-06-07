package com.sopt.now.compose.data

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
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
package com.sopt.now.compose.data

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
import retrofit2.Response

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {
    fun setUserLoggedIn(loggedIn: Boolean) {
        localDataSource.setUserLoggedIn(loggedIn)
    }

    fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

    fun logoutUser() {
        localDataSource.logoutUser()
    }

    fun saveUserData(user: User) {
        localDataSource.saveUserData(user)
    }

    fun updateUserPassword(newPassword: String) {
        localDataSource.updateUserPassword(newPassword)
    }

    fun setMemberId(memberId: String) {
        localDataSource.setMemberId(memberId)
    }

    fun getMemberId(): String? {
        return localDataSource.getMemberId()
    }

    suspend fun signUp(request: RequestSignUpDto): Response<ResponseDto> {
        return remoteDataSource.signUp(request)
    }

    suspend fun login(request: RequestLoginDto): Response<ResponseDto> {
        return remoteDataSource.login(request)
    }

    suspend fun getUserInfo(): Response<ResponseInfoDto> {
        return remoteDataSource.getUserInfo()
    }

    suspend fun changePassword(request: RequestChangePasswordDto): Response<ResponseDto> {
        return remoteDataSource.changePassword(request)
    }
}
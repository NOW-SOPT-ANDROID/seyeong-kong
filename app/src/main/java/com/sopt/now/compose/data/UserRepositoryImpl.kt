package com.sopt.now.compose.data

import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.network.request.RequestSignUpDto
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun setUserLoggedIn(loggedIn: Boolean) {
        localDataSource.setUserLoggedIn(loggedIn)
    }

    override fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

    override fun logoutUser() {
        localDataSource.logoutUser()
    }

    override fun saveUserData(user: User) {
        localDataSource.saveUserData(user)
    }

    override fun updateUserPassword(newPassword: String) {
        localDataSource.updateUserPassword(newPassword)
    }

    override fun setMemberId(memberId: String) {
        localDataSource.setMemberId(memberId)
    }

    override suspend fun signUp(request: RequestSignUpDto): Response<ResponseDto> {
        return remoteDataSource.signUp(request)
    }

    override suspend fun login(request: RequestLoginDto): Response<ResponseDto> {
        return remoteDataSource.login(request)
    }

    override suspend fun getUserInfo(): Response<ResponseInfoDto> {
        return remoteDataSource.getUserInfo()
    }

    override suspend fun changePassword(request: RequestChangePasswordDto): Response<ResponseDto> {
        return remoteDataSource.changePassword(request)
    }
}
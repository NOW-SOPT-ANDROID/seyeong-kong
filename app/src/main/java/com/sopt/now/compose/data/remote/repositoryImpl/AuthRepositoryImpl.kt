package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.local.UserDataStore
import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.RequestSignInEntity
import com.sopt.now.compose.domain.entity.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDataStore: UserDataStore
) : AuthRepository {
    override suspend fun signup(user: RequestUserEntity): Result<Response<BaseResponseWithoutDataDto>> =
        runCatching {
            authDataSource.postSignUp(user)
        }

    override suspend fun login(user: RequestSignInEntity): Result<Response<BaseResponseWithoutDataDto>> =
        runCatching {
            authDataSource.postSignIn(user)
        }

    override suspend fun changePassword(user: RequestChangePasswordEntity): Result<Response<BaseResponseWithoutDataDto>> =
        runCatching {
            authDataSource.postChangePassword(user)
        }

    override fun getId() = userDataStore.id
    override fun getPassword() = userDataStore.password
    override fun getNickname() = userDataStore.nickname
    override fun getPhoneNumber() = userDataStore.phoneNumber
    override fun setId(id: String) {
        userDataStore.id = id
    }

    override fun setPassword(password: String) {
        userDataStore.password = password
    }

    override fun setNickname(nickname: String) {
        userDataStore.nickname = nickname
    }

    override fun setPhoneNumber(phoneNumber: String) {
        userDataStore.phoneNumber = phoneNumber
    }

    override fun setUserId(userId: String) {
        userDataStore.userId = userId
    }

    override fun setUserLoggedIn(loggedIn: Boolean) {
        userDataStore.isLoggedIn = loggedIn
    }

    override fun clearInfo() {
        userDataStore.clearInfo()
    }

    companion object {
        const val HEADER = "location"
    }
}
package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.local.UserDataStore
import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.domain.entity.request.RequestChangePasswordEntity
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.domain.entity.Result
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDataStore: UserDataStore
) : AuthRepository {
    override suspend fun signup(user: RequestUserEntity): Result<Unit> {
        return runCatching {
            authDataSource.postSignUp(user)
        }.fold(
            onSuccess = { Result.Success(Unit) },
            onFailure = { Result.Error(it) }
        )
    }

    override suspend fun login(user: RequestSignInEntity): Result<Unit> {
        return runCatching {
            authDataSource.postSignIn(user)
        }.fold(
            onSuccess = { Result.Success(Unit) },
            onFailure = { Result.Error(it) }
        )
    }


    override suspend fun changePassword(user: RequestChangePasswordEntity): Result<Unit> {
        return runCatching {
            authDataSource.postChangePassword(user)
        }.fold(
            onSuccess = { Result.Success(Unit) },
            onFailure = { Result.Error(it) }
        )
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

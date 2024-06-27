package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.remote.datasource.MypageDataSource
import com.sopt.now.compose.data.remote.response.toResponseUserInfo
import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import com.sopt.now.compose.domain.repository.MypageRepository
import javax.inject.Inject

class MypageRepositoryImpl @Inject constructor(
    private val mypageDataSource: MypageDataSource
) : MypageRepository {
    override suspend fun getUserInfo(): Result<ResponseUserInfo> =
        runCatching {
            mypageDataSource.getUserInfo().data.toResponseUserInfo()
        }
}
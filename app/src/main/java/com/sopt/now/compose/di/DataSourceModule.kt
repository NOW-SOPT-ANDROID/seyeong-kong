package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.data.remote.datasource.MypageDataSource
import com.sopt.now.compose.data.remote.datasourceImpl.AuthDataSourceImpl
import com.sopt.now.compose.data.remote.datasourceImpl.MypageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindAuthDataSource(dataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindMypageDataSource(dataSourceImpl: MypageDataSourceImpl): MypageDataSource
}
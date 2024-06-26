package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.datasource.AuthDataSource
import com.sopt.now.compose.data.remote.datasourceImpl.AuthDataSourceImpl
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
}
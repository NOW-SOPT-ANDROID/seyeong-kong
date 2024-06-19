package com.sopt.now.compose.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.UserLocalDataSource
import com.sopt.now.compose.data.UserRemoteDataSource
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.HeaderInterceptor
import com.sopt.now.compose.network.service.AuthService
import com.sopt.now.compose.network.service.FollowerService
import com.sopt.now.compose.network.service.ServicePool
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(
        sharedPreferences: SharedPreferences
    ): HeaderInterceptor = HeaderInterceptor(sharedPreferences)


    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideFollowerService(retrofit: Retrofit): FollowerService = retrofit.create(FollowerService::class.java)

    @Provides
    @Singleton
    fun provideServicePool(
        authService: AuthService,
        followerService: FollowerService
    ): ServicePool = ServicePool(authService, followerService)

    @Provides
    @Singleton
    fun provideUserLocalDataSource(sharedPreferences: SharedPreferences): UserLocalDataSource =
        UserLocalDataSource(sharedPreferences)

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(servicePool: ServicePool): UserRemoteDataSource =
        UserRemoteDataSource(servicePool.authService)
}
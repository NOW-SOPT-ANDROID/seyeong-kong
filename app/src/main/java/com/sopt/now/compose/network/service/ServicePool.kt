package com.sopt.now.compose.network.service

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicePool @Inject constructor(
    val authService: AuthService,
    val followerService: FollowerService
)
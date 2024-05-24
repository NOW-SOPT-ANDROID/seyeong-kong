package com.sopt.now.compose.network.service

import com.sopt.now.compose.network.ApiFactory
import com.sopt.now.compose.network.FollowerApiFactory

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val followerService = FollowerApiFactory.create<FollowerService>()
}
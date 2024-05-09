package com.sopt.now.network.service

import com.sopt.now.network.ApiFactory
import com.sopt.now.network.FollowerApiFactory

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val followerService = FollowerApiFactory.create<FollowerService>()
}
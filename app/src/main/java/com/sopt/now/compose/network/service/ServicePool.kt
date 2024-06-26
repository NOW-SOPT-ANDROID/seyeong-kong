package com.sopt.now.compose.network.service

import javax.inject.Singleton

@Singleton
class ServicePool (
    val followerService: FollowerService
)
package com.sopt.now.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val authenticationId: String,
    val password: String,
)

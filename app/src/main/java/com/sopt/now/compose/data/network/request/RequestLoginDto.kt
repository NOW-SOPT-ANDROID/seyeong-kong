package com.sopt.now.compose.data.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val authenticationId: String,
    val password: String,
)

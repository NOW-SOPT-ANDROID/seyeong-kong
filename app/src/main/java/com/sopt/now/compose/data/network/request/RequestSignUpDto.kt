package com.sopt.now.compose.data.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
)
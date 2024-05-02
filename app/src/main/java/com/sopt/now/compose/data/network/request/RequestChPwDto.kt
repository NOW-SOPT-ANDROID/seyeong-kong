package com.sopt.now.compose.data.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestChPwDto(
    val previousPassword: String,
    val newPassword: String,
    val newPasswordVerification: String
)
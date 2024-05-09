package com.sopt.now.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestChangePwDto(
    val previousPassword: String,
    val newPassword: String,
    val newPasswordVerification: String
)
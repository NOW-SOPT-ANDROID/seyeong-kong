package com.sopt.now.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestChangePasswordDto(
    val previousPassword: String,
    val newPassword: String,
    val newPasswordVerification: String
)
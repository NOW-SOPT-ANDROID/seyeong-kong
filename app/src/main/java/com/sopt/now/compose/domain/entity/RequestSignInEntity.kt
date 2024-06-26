package com.sopt.now.compose.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInEntity(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
)
package com.sopt.now.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
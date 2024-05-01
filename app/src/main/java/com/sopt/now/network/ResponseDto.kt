package com.sopt.now.network

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val code: Int,
    val message: String,
)
package com.sopt.now.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val code: Int,
    val message: String,
)
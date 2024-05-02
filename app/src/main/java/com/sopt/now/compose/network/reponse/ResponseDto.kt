package com.sopt.now.compose.network.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val code: Int,
    val message: String,
)
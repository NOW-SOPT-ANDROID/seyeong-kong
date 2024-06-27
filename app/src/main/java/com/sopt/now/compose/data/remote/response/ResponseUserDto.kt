package com.sopt.now.compose.data.remote.response

import com.sopt.now.compose.domain.entity.response.ResponseUserInfo
import kotlinx.serialization.SerialName

data class ResponseUserDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)

fun ResponseUserDto.toResponseUserInfo() = ResponseUserInfo(
    authenticationId, nickname, phone
)
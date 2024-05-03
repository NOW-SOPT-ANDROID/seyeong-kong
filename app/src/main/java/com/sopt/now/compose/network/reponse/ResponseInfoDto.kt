package com.sopt.now.compose.network.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseInfoDto (
    val code: Int,
    val message: String,
    val data: UserInfo
)

@Serializable
data class UserInfo(
    val authenticationId: String,
    val nickname: String,
    val phone: String
)
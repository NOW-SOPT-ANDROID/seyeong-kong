package com.sopt.now.compose.data

import java.io.Serializable

data class User(
    val id: String,
    val password: String,
    val nickname: String,
    val phone: String,
) : Serializable
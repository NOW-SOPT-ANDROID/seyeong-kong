package com.sopt.now.compose

import java.io.Serializable

data class User(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
) : Serializable
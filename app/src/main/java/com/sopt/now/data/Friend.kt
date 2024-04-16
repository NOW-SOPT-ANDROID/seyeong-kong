package com.sopt.now.data

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImg: Int,
    val name: String,
    val selfDescription: String
)
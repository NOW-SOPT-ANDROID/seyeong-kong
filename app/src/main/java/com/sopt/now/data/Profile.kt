package com.sopt.now.data

import androidx.annotation.DrawableRes

data class Profile(
    @DrawableRes val profileImg: Int,
    val name: String,
    val selfDescription: String
)
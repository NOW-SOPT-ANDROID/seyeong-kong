package com.sopt.now.data

import androidx.annotation.DrawableRes

data class Friend(
    var id: Int,
    @DrawableRes val profileImg: Int,
    val name: String,
    val selfDescription: String
)
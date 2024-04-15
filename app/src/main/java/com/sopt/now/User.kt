package com.sopt.now

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String,
    var password: String,
    var nickname: String,
    var mbti: String
) : Parcelable
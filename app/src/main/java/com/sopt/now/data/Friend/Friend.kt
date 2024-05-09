package com.sopt.now.data.friend

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class Friend(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val profileImg: Int,
    val name: String,
    val selfDescription: String? = null,
)
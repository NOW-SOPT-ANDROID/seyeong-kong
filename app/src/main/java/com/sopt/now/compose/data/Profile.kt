package com.sopt.now.compose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class Profile(
    val profileImage: ImageVector,
    val name: String,
    val selfDescription: String,
)

val userProfile = Profile(
    profileImage = Icons.Filled.Person,
    name = "0se0",
    selfDescription = "NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID",
)
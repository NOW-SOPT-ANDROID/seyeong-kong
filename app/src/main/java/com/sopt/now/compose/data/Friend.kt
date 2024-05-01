package com.sopt.now.compose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class Friend(
    val profileImage: ImageVector,
    val name: String,
    val selfDescription: String,
)

val friendList = listOf(
    Friend(
        profileImage = Icons.Filled.Person,
        name = "1",
        selfDescription = "Sweet Child O' Mine",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "2",
        selfDescription = "AWOLNATION - Run",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "3",
        selfDescription = "Avril Lavigne - Sk8er Boi",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "4",
        selfDescription = "TEXAS HOLD 'EM",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "5",
        selfDescription = "Future, Metro Boomin, Kendrick Lamar - Like That",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "6",
        selfDescription = "Oasis - Don't Look Back in Anger",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "7",
        selfDescription = "Bring That Fire",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "8",
        selfDescription = "OneRepublic - Run",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "9",
        selfDescription = "Kate Bush - Running Up That Hill",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "10",
        selfDescription = "bbno$ - Edamame",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "11",
        selfDescription = "Let’s Get The Party Started (feat. Bring Me The Horizon)",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "12",
        selfDescription = "SVEA - All My Exes",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "13",
        selfDescription = "Everyday Feels Like Sunday",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "14",
        selfDescription = "24KGoldn, iann dior - Mood",
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "15",
        selfDescription = "Stacey Ryan - Fall In Love Alone",
    ),
)
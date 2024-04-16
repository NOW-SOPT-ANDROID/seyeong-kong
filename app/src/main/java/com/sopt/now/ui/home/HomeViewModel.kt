package com.sopt.now.ui.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend
import com.sopt.now.data.Profile

class HomeViewModel : ViewModel() {

    val mockFriendList = listOf(
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "1",
            selfDescription = "Sweet Child O' Mine",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "2",
            selfDescription = "AWOLNATION - Run",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "3",
            selfDescription = "Avril Lavigne - Sk8er Boi",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "4",
            selfDescription = "TEXAS HOLD 'EM",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "5",
            selfDescription = "Future, Metro Boomin, Kendrick Lamar - Like That",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "6",
            selfDescription = "Oasis - Don't Look Back in Anger",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "7",
            selfDescription = "Bring That Fire",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "8",
            selfDescription = "OneRepublic - Run",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "9",
            selfDescription = "Kate Bush - Running Up That Hill",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "10",
            selfDescription = "bbno$ - Edamame",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "11",
            selfDescription = "Let’s Get The Party Started (feat. Bring Me The Horizon)",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "12",
            selfDescription = "SVEA - All My Exes",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "13",
            selfDescription = "Everyday Feels Like Sunday",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "14",
            selfDescription = "24KGoldn, iann dior - Mood",
        ),
        Friend(
            profileImg = R.drawable.ic_mypage,
            name = "15",
            selfDescription = "Stacey Ryan - Fall In Love Alone",
        ),
    )

    val mockProfileList = listOf(
        Profile(
            profileImg = R.drawable.ic_mypage,
            name = "0se0",
            selfDescription = "NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID",
        ),
    )

}
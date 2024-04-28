package com.sopt.now.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend
import com.sopt.now.data.Profile

class HomeViewModel : ViewModel() {

    private val _mockFriendList = MutableLiveData<List<Friend>>()
    val mockFriendList: LiveData<List<Friend>> get() = _mockFriendList

    private val _mockProfileList = MutableLiveData<List<Profile>>()
    val mockProfileList: LiveData<List<Profile>> get() = _mockProfileList

    init {
        _mockFriendList.value = listOf(
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우",
            ),
            Friend(
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식",
            ),
        )

        _mockProfileList.value = listOf(
            Profile(
                profileImg = R.drawable.ic_mypage,
                name = "공세영",
                selfDescription = "NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID",
            ),
        )
    }
}
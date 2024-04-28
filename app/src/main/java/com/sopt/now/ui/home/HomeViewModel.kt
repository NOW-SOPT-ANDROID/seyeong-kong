package com.sopt.now.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend
import com.sopt.now.data.Profile


class HomeViewModel : ViewModel() {
    private val _friends = MutableLiveData(
        listOf(
            Friend(
                id = 1,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 2,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 3,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            ),
            Friend(
                id = 4,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 5,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 6,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            ),
            Friend(
                id = 7,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 8,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 9,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            ),
            Friend(
                id = 10,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 11,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 12,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            ),
            Friend(
                id = 13,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 14,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 15,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            ),
            Friend(
                id = 16,
                profileImg = R.drawable.ic_mypage,
                name = "임하늘",
                selfDescription = "임하늘마늘"
            ),
            Friend(
                id = 17,
                profileImg = R.drawable.ic_mypage,
                name = "우상욱",
                selfDescription = "우상욱상우"
            ),
            Friend(
                id = 18,
                profileImg = R.drawable.ic_mypage,
                name = "신민석",
                selfDescription = "신민석민식"
            )
        )
    )
    val friends: LiveData<List<Friend>> = _friends

    private val _profile = MutableLiveData(
        listOf(
            Profile(
                profileImg = R.drawable.ic_mypage,
                name = "공세영",
                selfDescription = "NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID NOW SOPT ANDROID",
            ),
        )
    )
    val profile: LiveData<List<Profile>> = _profile

    fun addFriend(friend: Friend) {
        val updatedList = _friends.value.orEmpty().toMutableList()
        updatedList.add(friend)
        _friends.value = updatedList
    }

    fun removeFriend(friend: Friend) {
        val updatedList = _friends.value.orEmpty().toMutableList()
        updatedList.remove(friend)
        _friends.value = updatedList
    }
}
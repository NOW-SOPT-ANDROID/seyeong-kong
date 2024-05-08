package com.sopt.now.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sopt.now.AppContainer
import com.sopt.now.R
import com.sopt.now.data.Profile
import com.sopt.now.data.friend.Friend
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : ViewModel() {
    private val appContainer: AppContainer = AppContainer(application.applicationContext)
    private val friendsRepository = appContainer.provideFriendsRepository()

    val friends: LiveData<List<Friend>> = friendsRepository.getAllFriends().asLiveData()

    fun addFriend(friend: Friend) {
        viewModelScope.launch {
            Log.d("HomeViewModel1", "친구 추가: $friend")
            friendsRepository.insertFriend(friend)
        }
    }

    fun deleteFriend(friend: Friend) {
        viewModelScope.launch {
            friendsRepository.deleteFriend(friend)
        }
    }

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
}
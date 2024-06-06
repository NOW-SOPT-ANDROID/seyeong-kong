package com.sopt.now.ui.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.network.response.ResponseFollowerDto
import com.sopt.now.network.service.ServicePool
import kotlinx.coroutines.launch
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    private val _followers = MutableLiveData<List<ResponseFollowerDto.Data>>()
    val followers: LiveData<List<ResponseFollowerDto.Data>> get() = _followers

    fun loadFollowers() {
        viewModelScope.launch {
            runCatching {
                ServicePool.followerService.getFollowers(2)
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {t ->
                Log.e("FollowerViewModel", "서버 에러", t)
            }
        }
    }

    private fun handleSuccess(response: Response<ResponseFollowerDto>) {
        if (response.isSuccessful) {
            response.body()?.data?.let { followers ->
                _followers.value = followers
            }
        } else {
            Log.e("FollowerViewModel", "Failed to fetch followers")
        }
    }
}

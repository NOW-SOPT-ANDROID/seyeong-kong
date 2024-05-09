package com.sopt.now.ui.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.network.response.ResponseFollowerDto
import com.sopt.now.network.service.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    private val _followers = MutableLiveData<List<ResponseFollowerDto.Data>>()
    val followers: LiveData<List<ResponseFollowerDto.Data>> get() = _followers

    fun loadFollowers() {
        ServicePool.followerService.getFollowers(2).enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { followers ->
                        _followers.value = followers
                    }
                } else {
                    Log.e("FollowerViewModel", "Failed to fetch followers")
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {
                Log.e("FollowerViewModel", "Error fetching followers", t)
            }
        })
    }
}

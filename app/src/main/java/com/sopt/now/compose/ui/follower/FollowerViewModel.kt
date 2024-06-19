package com.sopt.now.compose.ui.follower

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.network.reponse.ResponseFollowerDto
import com.sopt.now.compose.network.service.ServicePool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel  @Inject constructor(
    private val servicePool: ServicePool
) : ViewModel()  {
    private val _followers = MutableStateFlow<List<ResponseFollowerDto.Data>>(emptyList())
    val followers: StateFlow<List<ResponseFollowerDto.Data>> = _followers

    init {
        loadFollowers()
    }

    private fun loadFollowers() {
        servicePool.followerService.getFollowers(2).enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>,
            ) {
                if (response.isSuccessful) {
                    _followers.value = response.body()?.data.orEmpty()
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

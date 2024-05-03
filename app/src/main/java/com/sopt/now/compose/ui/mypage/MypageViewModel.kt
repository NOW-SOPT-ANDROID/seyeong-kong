package com.sopt.now.compose.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.SoptApp.Companion.userRepository
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.reponse.UserInfo
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.ui.AuthState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<AuthState>()
    val userLiveData = MutableLiveData<UserInfo>()

    fun info() {
        authService.info().enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        userLiveData.postValue(it)
                    }
                    liveData.value = AuthState(isSuccess = true, message = "회원 정보 조회 성공")
                } else {
                    liveData.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                liveData.value = AuthState(isSuccess = false, message = "서버 에러")
            }
        })
    }

    fun logout() {
        userRepository.logoutUser()
    }
}
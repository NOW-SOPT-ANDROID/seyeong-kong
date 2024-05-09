package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.Sopt.Companion.userRepository
import com.sopt.now.network.response.ResponseInfoDto
import com.sopt.now.network.response.UserInfo
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageViewModel: ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val userInfoStatus = MutableLiveData<AuthState>()
    val userLiveData = MutableLiveData<UserInfo?>()

    fun info() {
        authService.info().enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(call: Call<ResponseInfoDto>, response: Response<ResponseInfoDto>) {
                if (response.isSuccessful) {
                    val userInfo = response.body()?.data
                    if (userInfo != null) {
                        userLiveData.postValue(userInfo)
                        userInfoStatus.value = AuthState(isSuccess = true, message = "회원 정보 조회 성공")
                    } else {
                        userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 없음")
                    }
                } else {
                    userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                userInfoStatus.value = AuthState(isSuccess = false, message = "서버 에러")
            }
        })
    }

    fun logout() {
        userRepository.logoutUser()
    }
}
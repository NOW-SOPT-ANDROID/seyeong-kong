package com.sopt.now.compose.ui.mypage

import androidx.lifecycle.LiveData
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

    private val _mypageStatus = MutableLiveData<AuthState>()
    private val _userLiveData = MutableLiveData<UserInfo>()
    private val _successLogout = MutableLiveData<Boolean?>(null)

    val mypageStatus: LiveData<AuthState> = _mypageStatus
    val userLiveData: LiveData<UserInfo> = _userLiveData
    val successLogout: LiveData<Boolean?> = _successLogout

    fun userInfo() {
        authService.info().enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _userLiveData.setValue(it)
                    }
                    _mypageStatus.value = AuthState(isSuccess = true, message = "회원 정보 조회 성공")
                } else {
                    _mypageStatus.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                _mypageStatus.value = AuthState(isSuccess = false, message = "서버 에러")
            }
        })
    }

    fun logout() {
        if (userRepository.logoutUser()) {
            userRepository.setUserLoggedIn(false)
            _successLogout.value = true
        } else {
            _successLogout.value = false
        }
    }
}
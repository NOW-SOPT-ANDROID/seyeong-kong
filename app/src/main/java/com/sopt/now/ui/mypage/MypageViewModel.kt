package com.sopt.now.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.UserRepository
import com.sopt.now.network.response.ResponseInfoDto
import com.sopt.now.network.response.UserInfo
import com.sopt.now.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Response

class MypageViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val userInfoStatus = MutableLiveData<AuthState>()
    val userLiveData = MutableLiveData<UserInfo?>()

    fun userInfo() {
        viewModelScope.launch {
            runCatching {
                userRepository.getUserInfo()
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {
                userInfoStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(response: Response<ResponseInfoDto>) {
        if (response.isSuccessful) {
            successResponse(response)
        } else {
            failResponse()
        }
    }

    private fun successResponse(response: Response<ResponseInfoDto>) {
        val userInfo = response.body()?.data
        if (userInfo != null) {
            userLiveData.postValue(userInfo)
            userInfoStatus.value = AuthState(isSuccess = true, message = "회원 정보 조회 성공")
        } else {
            userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 없음")
        }
    }

    private fun failResponse() {
        userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
    }

    fun logout() {
        userRepository.logoutUser()
    }
}
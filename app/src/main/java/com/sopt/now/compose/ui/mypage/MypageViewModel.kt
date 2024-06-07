package com.sopt.now.compose.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.reponse.UserInfo
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Response

class MypageViewModel(
    private val userRepository: UserRepository,
) : ViewModel(){
    private val authService by lazy { ServicePool.authService }

    private val _userInfoStatus = MutableLiveData<AuthState>()
    private val _userLiveData = MutableLiveData<UserInfo>()
    private val _successLogout = MutableLiveData<Boolean?>(null)

    val userInfoStatus: LiveData<AuthState> = _userInfoStatus
    val userLiveData: LiveData<UserInfo> = _userLiveData
    val successLogout: LiveData<Boolean?> = _successLogout

    fun fetchUserInfo() {
        viewModelScope.launch {
            runCatching {
                authService.userInfo()
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {
                _userInfoStatus.value = AuthState (
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
        response.body()?.data?.let { userInfo ->
            _userLiveData.postValue(userInfo)
            _userInfoStatus.postValue(AuthState(isSuccess = true, message = "회원 정보 조회 성공"))
        } ?: run {
            _userInfoStatus.postValue(AuthState(isSuccess = false, message = "회원 정보 없음"))
        }
    }

    private fun failResponse() {
        _userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
    }

    fun logout() {
        userRepository.logoutUser()
        _successLogout.postValue(true)
    }
}
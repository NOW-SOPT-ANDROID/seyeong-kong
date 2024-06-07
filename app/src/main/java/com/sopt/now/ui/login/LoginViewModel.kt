package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.UserRepository
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val loginStatus = MutableLiveData<AuthState>()

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            runCatching {
                authService.login(request)
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {
                loginStatus.value = AuthState (
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(response: Response<ResponseDto>) {
        if (response.isSuccessful) {
            successResponse(response)
        } else {
            failResponse(response)
        }
    }
    private fun successResponse(response: Response<ResponseDto>) {
        val memberId = response.headers()["location"] ?: "unknown"
        userRepository.setUserLoggedIn(true)
        userRepository.setMemberId(memberId)

        loginStatus.value = AuthState(
            isSuccess = true,
            message = "로그인 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<ResponseDto>) {
        val error = response.message()
        loginStatus.value = AuthState(
            isSuccess = false,
            message = "로그인 실패 $error"
        )
    }
}
package com.sopt.now.compose.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _loginStatus = MutableLiveData<AuthState>()
    val loginStatus: LiveData<AuthState> = _loginStatus

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            runCatching {
                authService.login(request)
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {
                _loginStatus.value = AuthState(
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

        _loginStatus.value = AuthState(
            isSuccess = true,
            message = "로그인 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<ResponseDto>) {
        val error = response.message()
        _loginStatus.value = AuthState(
            isSuccess = false,
            message = "로그인 실패 $error"
        )
    }
}
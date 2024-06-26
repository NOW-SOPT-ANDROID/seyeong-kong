package com.sopt.now.compose.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.RequestSignInEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.ui.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginStatus = MutableLiveData<AuthState>()
    val loginStatus: LiveData<AuthState> = _loginStatus

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            runCatching {
                authRepository.verifyUser(
                    RequestSignInEntity(
                        authenticationId = request.authenticationId,
                        password = request.password
                    )
                )
            }.onSuccess { result ->
                result.onSuccess { response ->
                    handleSuccess(response)
                }
            }.onFailure {
                _loginStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(response: Response<BaseResponseWithoutDataDto>) {
        if (response.isSuccessful) {
            val memberId = response.headers()["location"] ?: "unknown"
            authRepository.setUserLoggedIn(true)
            authRepository.setUserId(memberId)

            _loginStatus.value = AuthState(
                isSuccess = true,
                message = "로그인 성공 유저의 ID는 $memberId 입니다."
            )
        } else {
            failResponse(response)
        }
    }

    private fun failResponse(response: Response<BaseResponseWithoutDataDto>) {
        val error = response.message()
        _loginStatus.value = AuthState(
            isSuccess = false,
            message = "로그인 실패 $error"
        )
    }
}
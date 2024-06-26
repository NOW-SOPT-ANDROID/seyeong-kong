package com.sopt.now.compose.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.model.User
import com.sopt.now.compose.network.request.RequestSignUpDto
import com.sopt.now.compose.ui.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signupStatus = MutableLiveData<AuthState>()
    val signupStatus: LiveData<AuthState> = _signupStatus

    fun signUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            runCatching {
                val userEntity = RequestUserEntity(
                    authenticationId = request.authenticationId,
                    password = request.password,
                    nickname = request.nickname,
                    phone = request.phone
                )
                authRepository.signup(userEntity)
            }.onSuccess { result ->
                result.fold(
                    onSuccess = { response ->
                        handleSuccess(response, request)
                    },
                    onFailure = {
                        _signupStatus.value = AuthState(
                            isSuccess = false,
                            message = "서버 에러"
                        )
                    }
                )
            }.onFailure {
                _signupStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(response: Response<BaseResponseWithoutDataDto>, request: RequestSignUpDto) {
        if (response.isSuccessful) {
            successResponse(response, request)
        } else {
            failResponse(response)
        }
    }

    private fun successResponse(response: Response<BaseResponseWithoutDataDto>, request: RequestSignUpDto) {
        val memberId = response.headers()["location"] ?: "unknown"
        val user = User(request.authenticationId, request.password, request.nickname, request.phone)
        authRepository.setUserId(memberId)
        authRepository.setId(request.authenticationId)
        authRepository.setPassword(request.password)
        authRepository.setNickname(request.nickname)
        authRepository.setPhoneNumber(request.phone)
        _signupStatus.value = AuthState(
            isSuccess = true,
            message = "회원가입 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<BaseResponseWithoutDataDto>) {
        val error = response.message()
        _signupStatus.value = AuthState(
            isSuccess = false,
            message = "회원가입 실패 $error"
        )
    }
}
package com.sopt.now.compose.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.request.RequestSignUpDto
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Response

class SignupViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _signupStatus = MutableLiveData<AuthState>()
    val signupStatus: LiveData<AuthState> = _signupStatus

    fun signUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            runCatching {
                userRepository.signUp(request)
            }.onSuccess { response ->
                handleSuccess(response, request)
            }.onFailure {
                _signupStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(response: Response<ResponseDto>, request: RequestSignUpDto) {
        if (response.isSuccessful) {
            successResponse(response, request)
        } else {
            failResponse(response)
        }
    }

    private fun successResponse(response: Response<ResponseDto>, request: RequestSignUpDto) {
        val memberId = response.headers()["location"] ?: "unknown"
        val user = User(request.authenticationId, request.password, request.nickname, request.phone)
        userRepository.saveUserData(user)
        _signupStatus.value = AuthState(
            isSuccess = true,
            message = "회원가입 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<ResponseDto>) {
        val error = response.message()
        _signupStatus.value = AuthState(
            isSuccess = false,
            message = "회원가입 실패 $error"
        )
    }
}
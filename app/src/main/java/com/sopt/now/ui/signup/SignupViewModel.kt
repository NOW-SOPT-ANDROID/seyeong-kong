package com.sopt.now.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.User
import com.sopt.now.data.UserRepository
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.ui.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val signupStatus = MutableLiveData<AuthState>()

    fun signUp(request: RequestSignUpDto) {
        viewModelScope.launch {
            runCatching {
                userRepository.signUp(request)
            }.onSuccess { response ->
                handleSuccess(response, request)
            }.onFailure {
                signupStatus.value = AuthState(
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
        signupStatus.value = AuthState(
            isSuccess = true,
            message = "회원가입 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<ResponseDto>) {
        val error = response.message()
        signupStatus.value = AuthState(
            isSuccess = false,
            message = "회원가입 실패 $error"
        )
    }
}
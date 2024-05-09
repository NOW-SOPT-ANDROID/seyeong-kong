package com.sopt.now.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.Sopt
import com.sopt.now.data.User
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val signupStatus = MutableLiveData<AuthState>()

    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    successResponse(response, request)
                } else {
                    failResponse(response)
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                signupStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }

    private fun successResponse(response: Response<ResponseDto>, request: RequestSignUpDto) {
        val memberId = response.headers()["location"] ?: "unknown"
        val user = User(request.authenticationId, request.password, request.nickname, request.phone)
        Sopt.userRepository.saveUserData(user)
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
package com.sopt.now.ui.signup

import android.util.Log
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

class SignupViewModel: ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val signupStatus = MutableLiveData<AuthState>()

    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val memberId = response.headers()["location"]?: "unknown"
                    val user = User(request.authenticationId, request.password, request.nickname, request.phone)
                    Sopt.userRepository.saveUserData(user)
                    signupStatus.value = AuthState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $memberId 입니다."
                    )
                    Log.d("SignUp", "data: $data, userId: $memberId")
                } else {
                    val statusCode = response.code()
                    val error = response.message()
                    signupStatus.value = AuthState(
                        isSuccess = false,
                        message = "회원가입 실패 $error"
                    )
                    Log.d("SignupViewModel", "회원가입 실패: HTTP Status Code: $statusCode, Error: $error")
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
}
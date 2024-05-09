package com.sopt.now.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.Sopt
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val loginStatus = MutableLiveData<AuthState>()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val memberId = response.headers()["location"]?: "unknown"
                    Sopt.userRepository.setUserLoggedIn(true)
                    Sopt.userRepository.setMemberId(memberId)
                    loginStatus.value = AuthState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $memberId 입니다."
                    )
                    Log.d("Login", "data: $data, userId: $memberId")
                } else {
                    val statusCode = response.code()
                    val error = response.message()
                    loginStatus.value = AuthState(
                        isSuccess = false,
                        message = "로그인 실패 $error"
                    )
                    Log.d("LoginViewModel", "로그인 실패: HTTP Status Code: $statusCode, Error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                loginStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}
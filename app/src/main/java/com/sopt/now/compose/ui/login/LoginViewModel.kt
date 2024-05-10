package com.sopt.now.compose.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.SoptApp
import com.sopt.now.compose.SoptApp.Companion.userRepository
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val authService by lazy { ServicePool.authService }

    private val _loginStatus = MutableLiveData<AuthState>()
    val loginStatus: LiveData<AuthState> = _loginStatus

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val memberId = response.headers()["location"] ?: "unknown"
                    userRepository.setUserLoggedIn(true)
                    userRepository.setMemberId(memberId)
                    _loginStatus.value = AuthState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $memberId 입니다."
                    )
                    Log.d("Login", "data: $data, userId: $memberId")
                } else {
                    val statusCode = response.code()
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    _loginStatus.value = AuthState(
                        isSuccess = false,
                        message = "로그인 실패 $error"
                    )
                    Log.d("LoginViewModel", "로그인 실패: HTTP Status Code: $statusCode, Error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _loginStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}
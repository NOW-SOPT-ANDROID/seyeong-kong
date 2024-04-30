package com.sopt.now.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Sopt
import com.sopt.now.network.RequestLoginDto
import com.sopt.now.network.ResponseDto
import com.sopt.now.network.ServicePool
import com.sopt.now.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<AuthState>()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val userId = response.headers()["location"]
                    Sopt.userRepository.setUserLoggedIn(true)
                    liveData.value = AuthState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $userId 입니다."
                    )
                    Log.d("Login", "data: $data, userId: $userId")
                } else {
                    val statusCode = response.code()
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    liveData.value = AuthState(
                        isSuccess = false,
                        message = "로그인 실패 $error"
                    )
                    Log.d("LoginViewModel", "로그인 실패: HTTP Status Code: $statusCode, Error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                liveData.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}
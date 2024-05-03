package com.sopt.now.compose.ui.ch_password

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.SoptApp
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.request.RequestChPwDto
import com.sopt.now.compose.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChPasswordViewModel: ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<AuthState>()

    fun chPassword(request: RequestChPwDto) {
        authService.chPassword(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val userId = response.headers()["location"]
                    SoptApp.userRepository.updateUserPassword(request.newPassword)
                    liveData.value = AuthState(
                        isSuccess = true,
                        message = "비밀번호 변경 성공 유저의 ID는 $userId 입니다."
                    )
                    Log.d("ChPassword", "data: $data, userId: $userId")
                } else {
                    val statusCode = response.code()
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    liveData.value = AuthState(
                        isSuccess = false,
                        message = "비밀번호 변경 실패 $error"
                    )
                    Log.d("ChPasswordViewModel", "비밀번호 변경 실패: HTTP Status Code: $statusCode, Error: $error")
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
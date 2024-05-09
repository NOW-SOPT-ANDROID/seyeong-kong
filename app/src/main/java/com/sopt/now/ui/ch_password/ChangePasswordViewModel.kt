package com.sopt.now.ui.ch_password

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.Sopt
import com.sopt.now.network.request.RequestChangePwDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel: ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val changePasswordStatus = MutableLiveData<AuthState>()

    fun changePassword(request: RequestChangePwDto) {
        authService.chPassword(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val memberId = response.headers()["location"]?: "unknown"
                    Sopt.userRepository.updateUserPassword(request.newPassword)
                    changePasswordStatus.value = AuthState(
                        isSuccess = true,
                        message = "비밀번호 변경 성공 유저의 ID는 $memberId 입니다."
                    )
                    Log.d("ChPassword", "data: $data, userId: $memberId")
                } else {
                    val statusCode = response.code()
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    changePasswordStatus.value = AuthState(
                        isSuccess = false,
                        message = "비밀번호 변경 실패 $error"
                    )
                    Log.d("ChPasswordViewModel", "비밀번호 변경 실패: HTTP Status Code: $statusCode, Error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                changePasswordStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}
package com.sopt.now.compose.ui.changePassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.SoptApp.Companion.userRepository
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val changePasswordStatus = MutableLiveData<AuthState>()

    fun changePassword(request: RequestChangePasswordDto) {
        authService.changePassword(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    userRepository.updateUserPassword(request.newPassword)
                    changePasswordStatus.value = AuthState(
                        isSuccess = true,
                        message = "비밀번호 변경 성공 유저의 ID는 $userId 입니다."
                    )
                } else {
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    changePasswordStatus.value = AuthState(
                        isSuccess = false,
                        message = "비밀번호 변경 실패 $error"
                    )
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
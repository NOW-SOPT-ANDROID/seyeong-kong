package com.sopt.now.ui.change_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.Sopt
import com.sopt.now.network.request.RequestChangePasswordDto
import com.sopt.now.network.response.ResponseDto
import com.sopt.now.network.service.ServicePool
import com.sopt.now.ui.AuthState
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
                    successResponse(response, request)
                } else {
                    failResponse(response)
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

    private fun successResponse(
        response: Response<ResponseDto>,
        request: RequestChangePasswordDto,
    ) {
        val memberId = response.headers()["location"] ?: "unknown"
        Sopt.userRepository.updateUserPassword(request.newPassword)
        changePasswordStatus.value = AuthState(
            isSuccess = true,
            message = "비밀번호 변경 성공 유저의 ID는 $memberId 입니다."
        )
    }

    private fun failResponse(response: Response<ResponseDto>) {
        val error = response.message()
        changePasswordStatus.value = AuthState(
            isSuccess = false,
            message = "비밀번호 변경 실패 $error"
        )
    }
}
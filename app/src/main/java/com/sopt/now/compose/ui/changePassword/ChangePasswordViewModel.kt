package com.sopt.now.compose.ui.changePassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.UserRepository
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.request.RequestChangePasswordDto
import com.sopt.now.compose.ui.AuthState
import kotlinx.coroutines.launch
import retrofit2.Response

class ChangePasswordViewModel(
    private val userRepository: UserRepository,
) : ViewModel()  {
    private val authService by lazy { ServicePool.authService }
    val changePasswordStatus = MutableLiveData<AuthState>()

    fun changePassword(request: RequestChangePasswordDto) {
        viewModelScope.launch {
            runCatching {
                authService.changePassword(request)
            }.onSuccess { response ->
                handleSuccess(response, request)
            }.onFailure {
                changePasswordStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        }
    }

    private fun handleSuccess(
        response: Response<ResponseDto>,
        request: RequestChangePasswordDto,
    ) {
        if (response.isSuccessful) {
            successResponse(response, request)
        } else {
            failResponse(response)
        }
    }

    private fun successResponse(
        response: Response<ResponseDto>,
        request: RequestChangePasswordDto,
    ) {
        val memberId = response.headers()["location"] ?: "unknown"
        userRepository.updateUserPassword(request.newPassword)
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
//package com.sopt.now.compose.ui.changePassword
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
//import com.sopt.now.compose.domain.entity.request.RequestChangePasswordEntity
//import com.sopt.now.compose.domain.repository.AuthRepository
//import com.sopt.now.compose.network.request.RequestChangePasswordDto
//import com.sopt.now.compose.ui.AuthState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class ChangePasswordViewModel @Inject constructor(
//    private val authRepository: AuthRepository,
//) : ViewModel() {
//    private val _changePasswordStatus = MutableLiveData<AuthState>()
//    val changePasswordStatus: LiveData<AuthState> = _changePasswordStatus
//
//    fun changePassword(request: RequestChangePasswordDto) {
//        viewModelScope.launch {
//            runCatching {
//                val requestEntity = RequestChangePasswordEntity(
//                    previousPassword = request.previousPassword,
//                    newPassword = request.newPassword,
//                    newPasswordVerification = request.newPasswordVerification
//                )
//                authRepository.changePassword(requestEntity)
//            }.onSuccess { response ->
//                handleSuccess(response)
//            }.onFailure {
//                _changePasswordStatus.value = AuthState(
//                    isSuccess = false,
//                    message = "서버 에러"
//                )
//            }
//        }
//    }
//
//    private fun handleSuccess(response: Result<Response<BaseResponseWithoutDataDto>>) {
//        response.onSuccess {
//            if (it.isSuccessful) {
//                val memberId = it.headers()["location"] ?: "unknown"
//                _changePasswordStatus.value = AuthState(
//                    isSuccess = true,
//                    message = "비밀번호 변경 성공 유저의 ID는 $memberId 입니다."
//                )
//            } else {
//                failResponse(it)
//            }
//        }.onFailure {
//            _changePasswordStatus.value = AuthState(
//                isSuccess = false,
//                message = "비밀번호 변경 실패 ${it.message}"
//            )
//        }
//    }
//
//    private fun failResponse(response: Response<BaseResponseWithoutDataDto>) {
//        val error = response.message()
//        _changePasswordStatus.value = AuthState(
//            isSuccess = false,
//            message = "비밀번호 변경 실패 $error"
//        )
//    }
//}
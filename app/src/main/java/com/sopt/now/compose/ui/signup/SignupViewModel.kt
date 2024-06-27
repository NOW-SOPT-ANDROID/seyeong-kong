package com.sopt.now.compose.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.remote.response.BaseResponseWithoutDataDto
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.ui.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState
    private val _signupStatus = MutableLiveData<AuthState>()
    val signupStatus: LiveData<AuthState> = _signupStatus

    private val _sideEffect = MutableSharedFlow<SignupSideEffect>()
    val sideEffect: SharedFlow<SignupSideEffect> = _sideEffect

    fun updateSignUpState(id: String? = null, password: String? = null, nickname: String? = null, phone: String? = null) {
        val currentState = _signUpState.value
        _signUpState.value = currentState.copy(
            id = id ?: currentState.id,
            password = password ?: currentState.password,
            nickname = nickname ?: currentState.nickname,
            phone = phone ?: currentState.phone
        )
    }

    fun signUp() {
        val currentState = _signUpState.value

        viewModelScope.launch {
            runCatching {
                val userEntity = RequestUserEntity(
                    authenticationId = currentState.id,
                    password = currentState.password,
                    nickname = currentState.nickname,
                    phone = currentState.phone
                )
                authRepository.signup(userEntity)
            }.onSuccess { result ->
                result.fold(
                    onSuccess = { response ->
                        handleSuccess(response, currentState)
                    },
                    onFailure = {
                        _sideEffect.emit(SignupSideEffect.ShowError("서버 에러"))
                    }
                )
            }.onFailure {
                _sideEffect.emit(SignupSideEffect.ShowError("서버 에러"))
            }
        }
    }

    private fun handleSuccess(response: Response<BaseResponseWithoutDataDto>, request: SignUpState) {
        if (response.isSuccessful) {
            successResponse(response, request)
        } else {
            failResponse(response)
        }
    }

    private fun successResponse(response: Response<BaseResponseWithoutDataDto>, request: SignUpState) {
        val memberId = response.headers()["location"] ?: "unknown"
        authRepository.setUserId(memberId)
        authRepository.setId(request.id)
        authRepository.setPassword(request.password)
        authRepository.setNickname(request.nickname)
        authRepository.setPhoneNumber(request.phone)
        _signupStatus.value = AuthState(
            isSuccess = true,
            message = "회원가입 성공 유저의 ID는 $memberId 입니다."
        )
        viewModelScope.launch {
            _sideEffect.emit(SignupSideEffect.NavigateToMain)
        }
    }

    private fun failResponse(response: Response<BaseResponseWithoutDataDto>) {
        val error = response.message()
        _signupStatus.value = AuthState(
            isSuccess = false,
            message = "회원가입 실패 $error"
        )
    }

    sealed class SignupSideEffect {
        data class ShowError(val message: String) : SignupSideEffect()
        data object NavigateToMain : SignupSideEffect()
    }
}
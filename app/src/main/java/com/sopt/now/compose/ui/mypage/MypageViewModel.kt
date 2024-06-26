package com.sopt.now.compose.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.domain.repository.AuthRepository
import com.sopt.now.compose.network.reponse.ResponseInfoDto
import com.sopt.now.compose.network.reponse.UserInfo
import com.sopt.now.compose.ui.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel(){
    private val _userInfoStatus = MutableLiveData<AuthState>()
    private val _userLiveData = MutableLiveData<UserInfo>()
    private val _successLogout = MutableLiveData<Boolean?>(null)

    val userInfoStatus: LiveData<AuthState> = _userInfoStatus
    val userLiveData: LiveData<UserInfo> = _userLiveData
    val successLogout: LiveData<Boolean?> = _successLogout

    fun fetchUserInfo() {
        viewModelScope.launch {
            runCatching {
                authRepository.getId()
            }.onSuccess { response ->
                handleSuccess(response)
            }.onFailure {
                _userInfoStatus.value = AuthState (
                    isSuccess = false,
                    message = "서버 에러"
                )

            }
        }
    }

    private fun handleSuccess(response: String) {
        if (response.isSuccessful) {
            successResponse(response)
        } else {
            failResponse()
        }
    }

    private fun successResponse(response: Response<ResponseInfoDto>) {
        response.body()?.data?.let { userInfo ->
            _userLiveData.postValue(userInfo)
            _userInfoStatus.postValue(AuthState(isSuccess = true, message = "회원 정보 조회 성공"))
        } ?: run {
            _userInfoStatus.postValue(AuthState(isSuccess = false, message = "회원 정보 없음"))
        }
    }

    private fun failResponse() {
        _userInfoStatus.value = AuthState(isSuccess = false, message = "회원 정보 조회 실패")
    }

    fun logout() {
        authRepository.clearInfo()
        _successLogout.postValue(true)
    }
}
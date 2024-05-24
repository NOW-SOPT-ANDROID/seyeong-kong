package com.sopt.now.compose.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.SoptApp.Companion.userRepository
import com.sopt.now.compose.data.User
import com.sopt.now.compose.network.request.RequestSignUpDto
import com.sopt.now.compose.network.reponse.ResponseDto
import com.sopt.now.compose.network.service.ServicePool
import com.sopt.now.compose.ui.AuthState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signupStatus = MutableLiveData<AuthState>()
    val signupStatus: LiveData<AuthState> = _signupStatus

    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val memberId = response.headers()["location"] ?: "unknown"
                    val user = User(
                        request.authenticationId,
                        request.password,
                        request.nickname,
                        request.phone
                    )
                    userRepository.saveUserData(user)
                    _signupStatus.value = AuthState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $memberId 입니다."
                    )
                    Log.d("SignUp", "data: $data, userId: $memberId")
                } else {
                    val statusCode = response.code()
                    val rawJson = response.errorBody()?.string() ?: "No error message provided"
                    val error = JSONObject(rawJson).optString("message", "error message")
                    _signupStatus.value = AuthState(
                        isSuccess = false,
                        message = "회원가입 실패 $error"
                    )
                    Log.d("Fail_signup", "회원가입 실패: HTTP Status Code: $statusCode, Error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _signupStatus.value = AuthState(
                    isSuccess = false,
                    message = "서버 에러"
                )
            }
        })
    }
}
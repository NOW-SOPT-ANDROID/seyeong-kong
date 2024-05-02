package com.sopt.now.compose.data.network

import com.sopt.now.compose.data.network.reponse.ResponseDto
import com.sopt.now.compose.data.network.request.RequestSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseDto>
}
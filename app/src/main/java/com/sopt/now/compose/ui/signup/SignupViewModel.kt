package com.sopt.now.compose.ui.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sopt.now.compose.R
import com.sopt.now.compose.data.User
import com.sopt.now.compose.data.UserRepository

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> = _signupResult

    private val _errorMsg = MutableLiveData<Int>()
    val errorMsg: LiveData<Int> = _errorMsg

    fun isValidFormData(id: String, pw: String, nickname: String, mbti: String) {
        if (isValidId(id) && isValidPassword(pw) && isValidNickname(nickname) && isValidMbti(mbti)) {
            val user = User(id, pw, nickname, mbti)
            userRepository.saveUserData(user)
            _signupResult.value = true
        }
    }

    private fun isValidId(id: String): Boolean {
        val idLength = id.length
        return when {
            idLength in MIN_ID_LENGTH..MAX_ID_LENGTH -> true
            idLength > MAX_ID_LENGTH -> {
                _errorMsg.value = R.string.id_less_than
                false
            }

            else -> {
                _errorMsg.value = R.string.id_greater_than
                false
            }
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val pwLength = password.length
        return when {
            pwLength in MIN_PW_LENGTH..MAX_PW_LENGTH -> true
            pwLength > MAX_PW_LENGTH -> {
                _errorMsg.value = R.string.pw_less_than
                false
            }

            else -> {
                _errorMsg.value = R.string.pw_greater_than
                false
            }

        }
    }

    private fun isValidNickname(nickname: String): Boolean {
        return if (nickname.isNotBlank()) {
            true
        } else {
            _errorMsg.value = R.string.input_nickname
            false
        }
    }

    private fun isValidMbti(mbti: String): Boolean {
        return if (mbti.isNotBlank() && mbti.length == MBTI_LENGTH) {
            true
        } else {
            _errorMsg.value = R.string.invalid_mbti
            false
        }
    }

    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PW_LENGTH = 8
        private const val MAX_PW_LENGTH = 12
        private const val MBTI_LENGTH = 4
    }
}
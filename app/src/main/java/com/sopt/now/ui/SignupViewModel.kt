package com.sopt.now.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.R
import com.sopt.now.User
import com.sopt.now.UserRepository

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> = _signupResult

    private val _errorMsg = MutableLiveData<Int>()
    val errorMsg: LiveData<Int> = _errorMsg

    fun isValidFormData(id: String, pw: String, nickname: String, mbti: String) {
        if (isValidId(id) && isValidPassword(pw) && isValidNickname(nickname) && isValidMbti(mbti)) {
            val user = User(id, pw, nickname, mbti)
            userRepository.saveUserDetails(user)
            _signupResult.value = true
        }
    }

    private fun isValidId(id: String): Boolean {
        val idLength = id.length
        return if (idLength in MIN_ID_LENGTH..MAX_ID_LENGTH) {
            true
        } else {
            _errorMsg.value = R.string.id_greater_than
            false
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val pwLength = password.length
        return if (pwLength in MIN_PW_LENGTH..MAX_PW_LENGTH) {
            true
        } else {
            _errorMsg.value = R.string.pw_greater_than
            false
        }
    }

    private fun isValidNickname(nickname: String): Boolean {
        return if (nickname.isNotBlank()) {
            true
        } else {
            _errorMsg.value = R.string.signup_et_nickname
            false
        }
    }

    private fun isValidMbti(mbti: String): Boolean {
        return if (mbti.isNotBlank() && mbti.length == MBTI_LENGTH) {
            true
        } else {
            _errorMsg.value = R.string.signup_et_mbti
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

@Suppress("UNCHECKED_CAST")
class SignupViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

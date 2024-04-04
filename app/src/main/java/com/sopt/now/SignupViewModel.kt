package com.sopt.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    private val _formState = MutableLiveData<FormState>()
    val isFormValid: LiveData<FormState> = _formState

    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PW_LENGTH = 8
        private const val MAX_PW_LENGTH = 12
        private const val MBTI_LENGTH = 4
    }

    fun validateFormData(id: String, password: String, nickname: String, mbti: String) {
        val idError = validateId(id)
        val passwordError = validatePassword(password)
        val nicknameError = validateNickname(nickname)
        val mbtiError = validateMbti(mbti)

        val errors = listOfNotNull(idError, passwordError, nicknameError, mbtiError)

        if (errors.isEmpty()) {
            _formState.value = FormState(isValid = true)
        } else {
            _formState.value = FormState(errorMsg = errors.first())
        }
    }

    private fun validateId(id: String): Int? =
        when {
            id.length < MIN_ID_LENGTH -> R.string.id_greater_than
            id.length > MAX_ID_LENGTH -> R.string.id_less_than
            else -> null
        }

    private fun validatePassword(password: String): Int? =
        when {
            password.length < MIN_PW_LENGTH -> R.string.pw_greater_than
            password.length > MAX_PW_LENGTH -> R.string.pw_less_than
            else -> null
        }

    private fun validateNickname(nickname: String): Int? =
        if (nickname.isBlank())
            R.string.signup_et_nickname
        else
            null

    private fun validateMbti(mbti: String): Int? =
        if (mbti.isBlank() || mbti.length != MBTI_LENGTH)
            R.string.invalid_mbti
        else
            null

    data class FormState(val isValid: Boolean = false, val errorMsg: Int? = null)
}
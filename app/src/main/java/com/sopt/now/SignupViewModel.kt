package com.sopt.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    private val _formState = MutableLiveData<FormState>()
    val isFormValid: LiveData<FormState> = _formState

    fun validateFormData(id: String, password: String, nickname: String, mbti: String) {
        val idError =
            if (id.length < 6) R.string.id_greater_than else if (id.length > 10) R.string.id_less_than else null
        val passwordError =
            if (password.length < 8) R.string.pw_greater_than else if (password.length > 12) R.string.pw_less_than else null
        val nicknameError = if (nickname.isBlank()) R.string.signup_et_nickname else null
        val mbtiError = if (mbti.isBlank() || mbti.length != 4) R.string.invalid_mbti else null

        val errors = listOfNotNull(idError, passwordError, nicknameError, mbtiError)

        if (errors.isEmpty()) {
            _formState.value = FormState(isValid = true)
        } else {
            _formState.value = FormState(errorMsg = errors.first())
        }
    }

    data class FormState(val isValid: Boolean = false, val errorMsg: Int? = null)
}

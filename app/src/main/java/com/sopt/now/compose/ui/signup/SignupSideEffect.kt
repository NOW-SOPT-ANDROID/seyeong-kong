package com.sopt.now.compose.ui.signup

sealed class SignupSideEffect {
        data class ShowError(val message: String) : SignupSideEffect()
        data object NavigateToMain : SignupSideEffect()
    }
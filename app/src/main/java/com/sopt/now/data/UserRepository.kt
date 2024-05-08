package com.sopt.now.data

import android.content.SharedPreferences

class UserRepository(private val preferences: SharedPreferences) {
    fun setUserLoggedIn(loggedIn: Boolean) {
        preferences.edit().putBoolean("isLoggedIn", loggedIn).commit()
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean("isLoggedIn", false)
    }

    fun getUserData(): User? {
        val userId = preferences.getString(KEY_USER_ID, null) ?: return null
        val password = preferences.getString(KEY_USER_PW, null) ?: return null
        val nickname = preferences.getString(KEY_NICKNAME, null) ?: return null
        val mbti = preferences.getString(KEY_MBTI, null) ?: return null
        return User(userId, password, nickname, mbti)
    }

    fun saveUserData(user: User) {
        preferences.edit().apply {
            putString(KEY_USER_ID, user.id)
            putString(KEY_USER_PW, user.password)
            putString(KEY_NICKNAME, user.nickname)
            putString(KEY_MBTI, user.mbti)
            apply()
        }
    }

    fun clearUserData() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_USER_ID = "UserID"
        private const val KEY_USER_PW = "Password"
        private const val KEY_NICKNAME = "Nickname"
        private const val KEY_MBTI = "Mbti"
    }
}

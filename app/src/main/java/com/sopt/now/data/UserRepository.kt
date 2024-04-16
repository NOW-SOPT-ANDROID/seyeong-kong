package com.sopt.now.data

import android.content.Context
import android.content.SharedPreferences

class UserRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)

    fun setUserLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun getUserData(): User? {
        val userId = sharedPreferences.getString(KEY_USER_ID, null) ?: return null
        val password = sharedPreferences.getString(KEY_USER_PW, null) ?: return null
        val nickname = sharedPreferences.getString(KEY_NICKNAME, null) ?: return null
        val mbti = sharedPreferences.getString(KEY_MBTI, null) ?: return null
        return User(userId, password, nickname, mbti)
    }

    fun saveUserData(user: User) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, user.id)
            putString(KEY_USER_PW, user.password)
            putString(KEY_NICKNAME, user.nickname)
            putString(KEY_MBTI, user.mbti)
            apply()
        }
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_USER_ID = "UserID"
        private const val KEY_USER_PW = "Password"
        private const val KEY_NICKNAME = "Nickname"
        private const val KEY_MBTI = "Mbti"
    }
}
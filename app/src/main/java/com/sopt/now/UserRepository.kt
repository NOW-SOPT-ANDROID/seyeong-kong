package com.sopt.now

import android.content.Context
import android.content.SharedPreferences

class UserRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)

    fun getUserDetails(): User? {
        val userId = sharedPreferences.getString(KEY_USER_ID, null) ?: return null
        val password = sharedPreferences.getString(KEY_USER_PW, "")
        val nickname = sharedPreferences.getString(KEY_NICKNAME, "")
        val mbti = sharedPreferences.getString(KEY_MBTI, "")
        return User(userId, password, nickname, mbti)
    }

    fun saveUserDetails(user: User) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, user.id)
            putString(KEY_USER_PW, user.password)
            putString(KEY_NICKNAME, user.nickname)
            putString(KEY_MBTI, user.mbti)
            apply()
        }
    }

    fun clearUserDetails() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_USER_ID = "UserID"
        private const val KEY_USER_PW = "Password"
        private const val KEY_NICKNAME = "Nickname"
        private const val KEY_MBTI = "Mbti"
    }
}

package com.sopt.now

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SaveLogin", Context.MODE_PRIVATE)

    fun getUserDetails(): User? {
        val userId = sharedPreferences.getString("UserID", null) ?: return null
        val password = sharedPreferences.getString("Password", "")
        val nickname = sharedPreferences.getString("Nickname", "")
        val mbti = sharedPreferences.getString("Mbti", "")

        return User(userId, password, nickname, mbti)
    }

    fun saveUserDetails(user: User) {
        sharedPreferences.edit().apply {
            putString("UserID", user.id)
            putString("Password", user.password)
            putString("Nickname", user.nickname)
            putString("Mbti", user.mbti)
            apply()
        }
    }

    fun clearUserDetails() {
        sharedPreferences.edit().clear().apply()
    }
}
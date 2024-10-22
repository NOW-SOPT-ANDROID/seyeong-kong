package com.sopt.now.data

import android.content.SharedPreferences
import androidx.core.content.edit

class UserRepository(private val preferences: SharedPreferences) {
    fun setUserLoggedIn(loggedIn: Boolean) {
        preferences.edit().putBoolean("isLoggedIn", loggedIn).commit()
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean("isLoggedIn", false)
    }

    fun logoutUser() {
        preferences.edit().clear().apply()
    }

    fun getUserData(): User? {
        val userId = preferences.getString(KEY_USER_ID, null) ?: return null
        val password = preferences.getString(KEY_USER_PW, null) ?: return null
        val nickname = preferences.getString(KEY_NICKNAME, null) ?: return null
        val phone = preferences.getString(KEY_PHONE, null) ?: return null
        return User(userId, password, nickname, phone)
    }

    fun saveUserData(user: User) {
        preferences.edit {
            putString(KEY_USER_ID, user.id)
            putString(KEY_USER_PW, user.password)
            putString(KEY_NICKNAME, user.nickname)
            putString(KEY_PHONE, user.phone)
        }
    }


    fun updateUserPassword(newPassword: String) {
        preferences.edit().putString(KEY_USER_PW, newPassword).apply()
    }

    fun setMemberId(memberId: String) {
        preferences.edit().putString(KEY_MEMBER_ID, memberId).apply()
    }

    fun getMemberId(): String? {
        return preferences.getString(KEY_MEMBER_ID, null)
    }


    companion object {
        private const val KEY_USER_ID = "UserID"
        private const val KEY_USER_PW = "Password"
        private const val  KEY_NICKNAME = "Nickname"
        private const val KEY_PHONE = "Phone"
        private const val KEY_MEMBER_ID = "MemberID"
    }
}

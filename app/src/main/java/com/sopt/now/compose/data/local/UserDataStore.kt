package com.sopt.now.compose.data.local

interface UserDataStore {
    var userId: String
    var id: String
    var password: String
    var nickname: String
    var phoneNumber: String
    var isLoggedIn: Boolean
    fun clearInfo()
}
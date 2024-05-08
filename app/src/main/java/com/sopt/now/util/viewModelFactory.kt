package com.sopt.now.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> viewModelFactory(crossinline creator: () -> T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U {
            if (modelClass.isAssignableFrom(T::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return creator() as U
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}
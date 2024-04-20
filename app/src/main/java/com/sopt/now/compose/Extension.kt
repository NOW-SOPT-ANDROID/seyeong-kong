package com.sopt.now.compose

import android.content.Intent
import android.os.Build

@Suppress("DEPRECATION")
inline fun <reified T : java.io.Serializable> Intent.getSerializableExtraProvider(
    identifierParameter: String
): T? {
    return if (Build.VERSION.SDK_INT >= 33) {
        this.getSerializableExtra(identifierParameter, T::class.java)
    } else {
        this.getSerializableExtra(identifierParameter) as T?
    }
}
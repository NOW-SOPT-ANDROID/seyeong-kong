package com.sopt.now

import android.content.Intent
import android.os.Build
import android.os.Parcelable

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Intent.getParcelableExtraProvider(identifierParameter: String): T? {
    return if (Build.VERSION.SDK_INT >= 33) {
        this.getParcelableExtra(identifierParameter, T::class.java)
    } else {
        this.getParcelableExtra(identifierParameter)
    }
}
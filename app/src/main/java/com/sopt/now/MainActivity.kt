package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        successMsg()
        setInfo()
        clickLogout()
    }

    private fun successMsg() {
        Snackbar.make(
            binding.root, R.string.success_login, Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setInfo() {
        val user: User? = intent.getParcelableExtraProvider("user")
        user?.let {
            binding.tvId.text = it.id
            binding.tvNickname.text = it.nickname
            binding.tvMbti.text = it.mbti
        }
    }

    private fun clickLogout() {
        binding.tvLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        clearData()
        goToLogin()
    }

    private fun clearData() {
        getSharedPreferences("SaveLogin", MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
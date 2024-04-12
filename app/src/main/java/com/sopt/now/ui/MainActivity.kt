package com.sopt.now.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.User
import com.sopt.now.UserRepository
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository(applicationContext)

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
        val user: User? = userRepository.getUserDetails()
        if (user != null) {
            binding.tvId.text = user.id
            binding.tvNickname.text = user.nickname
            binding.tvMbti.text = user.mbti
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun clickLogout() {
        binding.tvLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        userRepository.clearUserDetails()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
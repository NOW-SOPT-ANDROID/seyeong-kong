package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Snackbar.make(
            binding.root, R.string.success_login, Snackbar.LENGTH_SHORT
        ).show()

        val id = intent.getStringExtra("id")
        val nickname = intent.getStringExtra("nickname")
        val mbti = intent.getStringExtra("mbti")

        binding.tvId.text = id
        binding.tvNickname.text = nickname
        binding.tvMbti.text = mbti
    }
}
package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val nickname = intent.getStringExtra("nickname")
        val mbti = intent.getStringExtra("mbti")

        binding.tvId.text = id
        binding.tvNickname.text = nickname
        binding.tvMbti.text = mbti
    }
}
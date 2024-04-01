package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickSignupBtn()
    }

    private fun clickSignupBtn() {
        binding.btnSignup.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPassword.text.toString()
            val nickname = binding.etNickname.text.toString()
            val mbti = binding.etMbti.text.toString()

            if (isValidId(id) && isValidPassword(pw) && isvalidNickname(nickname) && isvalidMbti(mbti)) {
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("password", pw)
                    putExtra("nickname", nickname)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun isValidId(id: String): Boolean {
        val idLength = id.length
        val idMessage = when {
            idLength < 6 -> "아이디는 6글자 이상이어야 합니다."
            else -> "아이디는 10글자 이하여야 합니다."
        }

        return if (idLength in 6..10) {
            true
        } else {
            Snackbar.make(
                binding.root, idMessage, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val pwLength = password.length
        val pwMessage = when {
            pwLength < 8 -> "비밀번호는 8글자 이상이어야 합니다."
            else -> "비밀번호는 12글자 이하여야 합니다."
        }

        return if (pwLength in 8..12) {
            true
        } else {
            Snackbar.make(
                binding.root, pwMessage, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isvalidNickname(nickname: String): Boolean {
        return if (nickname.isNotBlank()) {
            true
        } else {
            Snackbar.make(
                binding.root, "닉네임을 입력해주세요.", Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isvalidMbti(mbti: String): Boolean {
        return if (mbti.isNotBlank() && mbti.length == 4) {
            true
        } else {
            Snackbar.make(
                binding.root, "mbti를 입력해주세요.", Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }
}
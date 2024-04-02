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

            if (isValidId(id) && isValidPassword(pw) && isValidNickname(nickname) && isValidMbti(
                    mbti
                )
            ) {
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
            idLength < 6 -> R.string.id_greater_than
            else -> R.string.id_less_than
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
            pwLength < 8 -> R.string.pw_greater_than
            else -> R.string.pw_less_than
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

    private fun isValidNickname(nickname: String): Boolean {
        return if (nickname.isNotBlank()) {
            true
        } else {
            Snackbar.make(
                binding.root, R.string.signup_et_nickname, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isValidMbti(mbti: String): Boolean {
        return if (mbti.isNotBlank() && mbti.length == 4) {
            true
        } else {
            Snackbar.make(
                binding.root, R.string.signup_et_mbti, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }
}
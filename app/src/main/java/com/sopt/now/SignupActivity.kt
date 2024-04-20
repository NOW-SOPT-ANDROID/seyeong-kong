package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PW_LENGTH = 8
        private const val MAX_PW_LENGTH = 12
        private const val MBTI_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupBtnClickListener()
    }

    private fun initSignupBtnClickListener() {
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
                    putExtra("mbti", mbti)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun isValidId(id: String): Boolean {
        val idLength = id.length
        val idMessage = when {
            idLength < MIN_ID_LENGTH -> R.string.id_greater_than
            else -> R.string.id_less_than
        }

        return if (idLength in MIN_ID_LENGTH..MAX_ID_LENGTH) {
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
            pwLength < MIN_PW_LENGTH -> R.string.pw_greater_than
            else -> R.string.pw_less_than
        }

        return if (pwLength in MIN_PW_LENGTH..MAX_PW_LENGTH) {
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
        return if (mbti.isNotBlank() && mbti.length == MBTI_LENGTH) {
            true
        } else {
            Snackbar.make(
                binding.root, R.string.signup_et_mbti, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }
}
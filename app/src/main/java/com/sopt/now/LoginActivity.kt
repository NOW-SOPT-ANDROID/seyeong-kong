package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String = ""
    private var pw: String = ""
    private var nickname: String = ""
    private var mbti: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupClickListener()
        setResultSignUp()
        initLoginBtnClickListener()
    }

    private fun initSignupClickListener() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    pw = result.data?.getStringExtra("password") ?: ""
                    nickname = result.data?.getStringExtra("nickname") ?: ""
                    mbti = result.data?.getStringExtra("mbti") ?: ""
                    binding.etId.setText(id)
                    binding.etPassword.setText(pw)
                }
            }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLogin.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputPw = binding.etPassword.text.toString()

            if (inputId == id && inputPw == pw && inputId.isNotBlank() && inputPw.isNotBlank()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("nickname", nickname)
                intent.putExtra("mbti", mbti)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    R.string.fail_login,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
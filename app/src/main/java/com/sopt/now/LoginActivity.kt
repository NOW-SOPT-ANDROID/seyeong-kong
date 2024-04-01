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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToSignup()
        setResultSignUp()
        loginBtn()
    }

    private fun goToSignup() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result : ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    pw = result.data?.getStringExtra("password") ?: ""
                    nickname = result.data?.getStringExtra("nickname") ?: ""
                    binding.etId.setText(id)
                    binding.etPassword.setText(pw)
                }
            }
    }

    private fun loginBtn() {
        binding.btnLogin.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputPw = binding.etPassword.text.toString()

            if (inputId == id && inputPw == pw) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("nickname", nickname)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디나 비밀번호가 잘못되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
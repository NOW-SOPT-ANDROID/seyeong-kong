package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToSignup()
        setResultSignUp()
        loginBtn()
        observeLoginResult()
    }

    private fun goToSignup() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val receivedId = result.data?.getStringExtra("id") ?: ""
                    val receivedPw = result.data?.getStringExtra("password") ?: ""
                    val receivedNickname = result.data?.getStringExtra("nickname") ?: ""
                    val receivedMbti = result.data?.getStringExtra("mbti") ?: ""
                    viewModel.setUserDetails(receivedId, receivedPw, receivedNickname, receivedMbti)
                    binding.etId.setText(receivedId)
                    binding.etPassword.setText(receivedPw)
                }
            }
    }

    private fun loginBtn() {
        binding.btnLogin.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputPw = binding.etPassword.text.toString()
            viewModel.login(inputId, inputPw)
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this) { isSuccess ->
            if (isSuccess) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("id", viewModel.id)
                    putExtra("nickname", viewModel.nickname)
                    putExtra("mbti", viewModel.mbti)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, R.string.fail_login, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
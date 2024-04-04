package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("SaveLogin", MODE_PRIVATE)

        checkLogin()
        goToSignup()
        setResultSignUp()
        initLoginBtnClickListener()
        observeLoginResult()
    }

    private fun checkLogin() {
        val sharedPreferences = getSharedPreferences("SaveLogin", MODE_PRIVATE)
        val userId = sharedPreferences.getString("UserID", null)
        val password = sharedPreferences.getString("Password", "")
        val nickname = sharedPreferences.getString("Nickname", "")
        val mbti = sharedPreferences.getString("Mbti", "")

        if (userId != null && password != null && nickname != null && mbti != null) {
            goToMain(User(userId, password, nickname, mbti))
        }
    }

    private fun goToMain(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
        finish()
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
                    val user: User? = result.data?.getParcelableExtraProvider("user") as? User

                    user?.let {
                        viewModel.setUserDetails(it)
                        binding.etId.setText(it.id)
                        binding.etPassword.setText(it.password)
                        saveLogin(it)
                    }
                }
            }
    }

    private fun initLoginBtnClickListener() {
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
                    viewModel.user?.let { user ->
                        putExtra("user", user)
                    }
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, R.string.fail_login, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLogin(user: User) {
        sharedPreferences.edit().apply {
            putString("UserID", user.id)
            putString("Password", user.password)
            putString("Nickname", user.nickname)
            putString("Mbti", user.mbti)
            apply()
        }
    }
}
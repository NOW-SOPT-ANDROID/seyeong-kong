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
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesManager = SharedPreferencesManager(this)

        checkLogin()
        initSignUpClickListener()
        setResultSignUp()
        initLoginBtnClickListener()
        observeLoginResult()
    }

    private fun checkLogin() {
        val user = sharedPreferencesManager.getUserDetails()
        user?.let {
            goToMain(it)
        }
    }

    private fun goToMain(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
        finish()
    }

    private fun initSignUpClickListener() {
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
        sharedPreferencesManager.saveUserDetails(user)
    }
}
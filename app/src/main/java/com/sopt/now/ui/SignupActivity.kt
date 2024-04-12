package com.sopt.now.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupClickListener()
        observeFormValidation()
    }

    private fun initSignupClickListener() {
        binding.btnSignup.setOnClickListener {
            val userId = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val nickname = binding.etNickname.text.toString()
            val mbti = binding.etMbti.text.toString()

            viewModel.validateFormData(userId, password, nickname, mbti)
        }
    }

    private fun observeFormValidation() {
        viewModel.isFormValid.observe(this) { formState ->
            formState.errorMsg?.let { errorMsgResId ->
                Snackbar.make(binding.root, getString(errorMsgResId), Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.userCreated.observe(this) { user ->
            user?.let {
                val intent = Intent().apply {
                    putExtra("user", it)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
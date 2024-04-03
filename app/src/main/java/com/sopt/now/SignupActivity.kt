package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signupBtn()
        observeFormValidation()
    }

    private fun signupBtn() {
        binding.btnSignup.setOnClickListener {
            viewModel.validateFormData(
                binding.etId.text.toString(),
                binding.etPassword.text.toString(),
                binding.etNickname.text.toString(),
                binding.etMbti.text.toString()
            )
        }
    }

    private fun observeFormValidation() {
        viewModel.isFormValid.observe(this) { formState ->
            formState.errorMsg?.let {
                Snackbar.make(binding.root, getString(it), Snackbar.LENGTH_LONG).show()
            } ?: run {
                if (formState.isValid) {
                    val intent = Intent().apply {
                        putExtra("id", binding.etId.text.toString())
                        putExtra("password", binding.etPassword.text.toString())
                        putExtra("nickname", binding.etNickname.text.toString())
                        putExtra("mbti", binding.etMbti.text.toString())
                    }
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}
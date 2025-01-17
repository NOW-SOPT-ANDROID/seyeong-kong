package com.sopt.now.ui.signup

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentSignupBinding
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.util.viewModelFactory

class SignupFragment : BaseFragment<FragmentSignupBinding>(
    FragmentSignupBinding::inflate
) {

    private val signupViewModel: SignupViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        viewModelFactory { SignupViewModel(app.appContainer.userRepository) }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSignupBtnClickListener()
        observeViewModel()
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignup.setOnClickListener {
            signupViewModel.signUp(getSignUpRequestDto())
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etId.text.toString()
        val password = binding.etPassword.text.toString()
        val nickname = binding.etNickname.text.toString()
        val phoneNumber = binding.etPhone.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }

    private fun observeViewModel() {
        signupViewModel.signupStatus.observe(viewLifecycleOwner) { signupState ->
            if (signupState.isSuccess) {
                Snackbar.make(binding.root, signupState.message, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signup_to_login)
            } else {
                Snackbar.make(binding.root, signupState.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
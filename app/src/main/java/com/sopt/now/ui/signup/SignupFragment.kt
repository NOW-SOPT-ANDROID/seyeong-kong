package com.sopt.now.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.network.request.RequestSignUpDto
import com.sopt.now.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding
        get() = requireNotNull(_binding) { "FragmentSignupBinding is not initialized" }
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSignupBtnClickListener()
        observeViewModel()
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignup.setOnClickListener {
            viewModel.signUp(getSignUpRequestDto())
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
        viewModel.liveData.observe(viewLifecycleOwner) { signUpState ->
            if (signUpState.isSuccess) {
                Snackbar.make(binding.root, signUpState.message, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signup_to_login)
            } else {
                Snackbar.make(binding.root, signUpState.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
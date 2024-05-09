package com.sopt.now.ui.login

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentLoginBinding
import com.sopt.now.network.request.RequestLoginDto
import com.sopt.now.util.viewModelFactory

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    private val loginViewModel: LoginViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        viewModelFactory { LoginViewModel(app.appContainer.userRepository) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initLoginBtn()
        initSignupNavigation()
    }

    private fun observeViewModel() {
        loginViewModel.loginStatus.observe(viewLifecycleOwner) { loginState ->
            if (loginState.isSuccess) {
                findNavController().navigate(R.id.action_login_to_home)
                Snackbar.make(binding.root, loginState.message, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, loginState.message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPassword.text.toString()
            loginViewModel.login(RequestLoginDto(authenticationId = id, password = pw))
        }
    }

    private fun initSignupNavigation() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }
}
package com.sopt.now.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.Sopt
import com.sopt.now.databinding.FragmentLoginBinding
import com.sopt.now.network.request.RequestLoginDto

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = requireNotNull(_binding) { "FragmentLoginBinding is not initialized" }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
        observeViewModel()
        initLoginBtn()
        initSignupNavigation()
    }

    private fun setUserInfo() {
        val user = Sopt.userRepository.getUserData()
        user?.let {
            binding.etId.setText(it.id)
            binding.etPassword.setText(it.password)
        }
    }

    private fun observeViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) { authState ->
            if(authState.isSuccess) {
                findNavController().navigate(R.id.action_login_to_home)
                Snackbar.make(binding.root, authState.message, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, authState.message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPassword.text.toString()
            viewModel.login(RequestLoginDto(authenticationId = id, password = pw))
        }
    }

    private fun initSignupNavigation() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
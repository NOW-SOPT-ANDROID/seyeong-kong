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
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentSignupBinding
import com.sopt.now.util.viewModelFactory

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding
        get() = requireNotNull(_binding) { "FragmentSignupBinding is not initialized" }

    private val signupViewModel: SignupViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        viewModelFactory { SignupViewModel(app.appContainer.userRepository) }
    }
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
            with(binding) {
                val id = etId.text.toString()
                val pw = etPassword.text.toString()
                val nickname = etNickname.text.toString()
                val mbti = etMbti.text.toString()

                signupViewModel.isValidFormData(id, pw, nickname, mbti)
            }
        }
    }

    private fun observeViewModel() {
        signupViewModel.signupResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                findNavController().navigate(R.id.action_signup_to_login)
            }
        }

        signupViewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
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
            with(binding) {
                val id = etId.text.toString()
                val pw = etPassword.text.toString()
                val nickname = etNickname.text.toString()
                val mbti = etMbti.text.toString()

                viewModel.isValidFormData(id, pw, nickname, mbti)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.signupResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                findNavController().navigate(R.id.action_signup_to_login)
            }
        }

        viewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
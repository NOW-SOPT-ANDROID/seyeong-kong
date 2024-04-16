package com.sopt.now.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.data.UserRepository
import com.sopt.now.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = requireNotNull(_binding) { "FragmentLoginBinding is not initialized" }

    private lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        userRepository = UserRepository(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = userRepository.getUserData()
        if (user != null) {
            setData(user)
            initLoginBtnClickListener(user)
            initSignupAlertClickListener()
        } else {
            initSignupNavClickListener()
            initLoginBtnAlertClickListener()
        }
    }

    private fun setData(user: User) {
        binding.etId.setText(user.id)
        binding.etPassword.setText(user.password)
    }

    private fun initLoginBtnClickListener(user: User?) {
        binding.btnLogin.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputPw = binding.etPassword.text.toString()

            if (inputId == user?.id && inputPw == user.password) {
                userRepository.setUserLoggedIn(true)
                findNavController().navigate(R.id.action_login_to_home, null, NavOptions.Builder()
                    .setPopUpTo(R.id.graph, true)
                    .build())
            } else {
                Snackbar.make(
                    binding.root,
                    R.string.fail_login,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initSignupAlertClickListener() {
        binding.tvSignup.setOnClickListener {
            Snackbar.make(
                binding.root,
                R.string.already_signup,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun initSignupNavClickListener() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }

    private fun initLoginBtnAlertClickListener() {
        binding.btnLogin.setOnClickListener {
            Snackbar.make(
                binding.root,
                R.string.not_signup,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
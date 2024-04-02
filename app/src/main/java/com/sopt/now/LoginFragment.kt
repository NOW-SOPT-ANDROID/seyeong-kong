package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToSignup()
        setIdPw()
        loginBtn()

    }

    private fun goToSignup() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }

    private fun setIdPw() {
        userViewModel.userId.observe(viewLifecycleOwner) { id ->
            binding.etId.setText(id)
        }
        userViewModel.userPassword.observe(viewLifecycleOwner) { password ->
            binding.etPassword.setText(password)
        }

    }

    private fun loginBtn() {
        binding.btnLogin.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputPw = binding.etPassword.text.toString()

            if (inputId == userViewModel.userId.value && inputPw == userViewModel.userPassword.value) {
                findNavController().navigate(R.id.action_login_to_profile)
            } else {
                Snackbar.make(
                    binding.root,
                    R.string.fail_login,
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
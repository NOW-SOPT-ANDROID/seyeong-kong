package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding ?= null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickSignupBtn()
    }

    private fun clickSignupBtn() {
        binding.btnSignup.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPassword.text.toString()
            val nickname = binding.etNickname.text.toString()
            val mbti = binding.etMbti.text.toString()

            if (isValidId(id) && isValidPassword(pw) && isValidNickname(nickname) && isValidMbti(mbti)) {
                userViewModel.userId.value = id
                userViewModel.userPassword.value = pw
                userViewModel.userNickname.value = nickname
                userViewModel.userMbti.value = mbti
                findNavController().navigate(R.id.action_signup_to_login)
            }
        }
    }

    private fun isValidId(id: String): Boolean {
        val idLength = id.length
        val idMessage = when {
            idLength < 6 -> R.string.id_greater_than
            else -> R.string.id_less_than
        }

        return if (idLength in 6..10) {
            true
        } else {
            Snackbar.make(
                binding.root, idMessage, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val pwLength = password.length
        val pwMessage = when {
            pwLength < 8 -> R.string.pw_greater_than
            else -> R.string.pw_less_than
        }

        return if (pwLength in 8..12) {
            true
        } else {
            Snackbar.make(
                binding.root, pwMessage, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isValidNickname(nickname: String): Boolean {
        return if (nickname.isNotBlank()) {
            true
        } else {
            Snackbar.make(
                binding.root, R.string.signup_et_nickname, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun isValidMbti(mbti: String): Boolean {
        return if (mbti.isNotBlank() && mbti.length == 4) {
            true
        } else {
            Snackbar.make(
                binding.root, R.string.signup_et_mbti, Snackbar.LENGTH_SHORT
            ).show()
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
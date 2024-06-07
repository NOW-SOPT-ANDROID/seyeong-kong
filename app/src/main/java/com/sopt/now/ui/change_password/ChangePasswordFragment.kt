package com.sopt.now.ui.change_password

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentChangePasswordBinding
import com.sopt.now.network.request.RequestChangePasswordDto

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(
    FragmentChangePasswordBinding::inflate
) {
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        app.serviceLocator.appViewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChangePasswordButtonClickListener()
        observeViewModel()
    }

    private fun initChangePasswordButtonClickListener() {
        binding.btnChPw.setOnClickListener {
            changePasswordViewModel.changePassword(getChangePasswordDto())
        }
    }

    private fun getChangePasswordDto(): RequestChangePasswordDto {
        val previousPassword = binding.etPrePw.text.toString()
        val newPassword = binding.etNewPw.text.toString()
        val newPasswordVerification = binding.etVerifyPw.text.toString()
        return RequestChangePasswordDto(
            previousPassword = previousPassword,
            newPassword = newPassword,
            newPasswordVerification = newPasswordVerification
        )
    }

    private fun observeViewModel() {
        changePasswordViewModel.changePasswordStatus.observe(viewLifecycleOwner) { changePwState ->
            if (changePwState.isSuccess) {
                Snackbar.make(binding.root, changePwState.message, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_changePassword_to_login)
            } else {
                Snackbar.make(binding.root, changePwState.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
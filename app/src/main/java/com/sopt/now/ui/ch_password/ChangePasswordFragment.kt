package com.sopt.now.ui.ch_password

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.databinding.FragmentChangePasswordBinding
import com.sopt.now.network.request.RequestChangePwDto

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding> (
    FragmentChangePasswordBinding::inflate
) {
    private val viewModel: ChangePasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChangePwButtonClickListener()
        observeViewModel()
    }

    private fun initChangePwButtonClickListener() {
        binding.btnChPw.setOnClickListener {
            val pre = binding.etPrePw.text.toString()
            val new = binding.etNewPw.text.toString()
            val verify = binding.etVerifyPw.text.toString()
            viewModel.changePassword(RequestChangePwDto(previousPassword = pre, newPassword = new, newPasswordVerification = verify))
        }
    }

    private fun observeViewModel() {
        viewModel.changePasswordStatus.observe(viewLifecycleOwner) { changePwState ->
            if (changePwState.isSuccess) {
                Snackbar.make(binding.root, changePwState.message, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_chPassword_to_login)
            } else {
                Snackbar.make(binding.root, changePwState.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
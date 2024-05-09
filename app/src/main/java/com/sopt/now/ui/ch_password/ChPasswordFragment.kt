package com.sopt.now.ui.ch_password

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.databinding.FragmentChPasswordBinding
import com.sopt.now.network.request.RequestChPwDto

class ChangePasswordFragment : BaseFragment<FragmentChPasswordBinding> (
    FragmentChPasswordBinding::inflate
) {
    private val viewModel: ChPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChPwBtnClickListener()
        observeViewModel()
    }

    private fun initChPwBtnClickListener() {
        binding.btnChPw.setOnClickListener {
            val pre = binding.etPrePw.text.toString()
            val new = binding.etNewPw.text.toString()
            val verify = binding.etVerifyPw.text.toString()
            viewModel.chPassword(RequestChPwDto(previousPassword = pre, newPassword = new, newPasswordVerification = verify))
        }
    }

    private fun observeViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) { chPwState ->
            if (chPwState.isSuccess) {
                Snackbar.make(binding.root, chPwState.message, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_chPassword_to_login)
            } else {
                Snackbar.make(binding.root, chPwState.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
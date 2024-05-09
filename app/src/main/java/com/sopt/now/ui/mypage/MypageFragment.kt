package com.sopt.now.ui.mypage

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.util.viewModelFactory

class MypageFragment : BaseFragment<FragmentMypageBinding> (
    FragmentMypageBinding::inflate
) {

    private val mypageViewModel: MypageViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        viewModelFactory { MypageViewModel(app.appContainer.userRepository) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mypageViewModel.info()
        setupObservers()
        initLogoutBtnClickListener()
        initChPasswordBtnClickListener()
        initFollowerClickListener()
    }

    private fun setupObservers() {
        mypageViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvId.text = it.authenticationId
                binding.tvNickname.text = it.nickname
                binding.tvPhone.text = it.phone
            }
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.tvLogout.setOnClickListener {
            mypageViewModel.logout()
            findNavController().navigate(R.id.action_mypage_to_login)
        }
    }

    private fun initChPasswordBtnClickListener() {
        binding.tvChPassword.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_to_changePassword)
        }
    }

    private fun initFollowerClickListener() {
        binding.tvFollower.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_to_follower)
        }
    }
}
package com.sopt.now.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "FragmentMypageBinding is not initialized" }
    private val viewModel: MypageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.info()
        observeViewModel()
        initLogoutBtnClickListener()
        initChPasswordBtnClickListener()
        initFollowerClickListener()
    }

    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvId.text = it.authenticationId
                binding.tvNickname.text = it.nickname
                binding.tvPhone.text = it.phone
            }
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.tvLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_mypage_to_login)
        }
    }

    private fun initChPasswordBtnClickListener() {
        binding.tvChPassword.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_to_chPassword)
        }
    }

    private fun initFollowerClickListener() {
        binding.tvFollower.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_to_follower)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
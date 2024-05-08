package com.sopt.now.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.util.viewModelFactory

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "FragmentMypageBinding is not initialized" }

    private val mypageViewModel: MypageViewModel by viewModels {
        val app = requireActivity().application as SoptApp
        viewModelFactory { MypageViewModel(app.appContainer.userRepository) }
    }

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

        setupObservers()
        initLogoutBtnClickListener()
    }

    private fun setupObservers() {
        mypageViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvId.text = it.id
                binding.tvMbti.text = it.mbti
                binding.tvNickname.text = it.nickname
            }
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.tvLogout.setOnClickListener {
            mypageViewModel.logout()
            findNavController().navigate(R.id.action_mypage_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
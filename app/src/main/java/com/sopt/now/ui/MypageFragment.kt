package com.sopt.now.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sopt.now.R
import com.sopt.now.User
import com.sopt.now.UserRepository
import com.sopt.now.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository


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

        userRepository = UserRepository(requireContext())
        val user = userRepository.getUserDetails()
        setUserData(user)
        initLogoutBtnClickListener()
    }

    private fun setUserData(user: User?) {
        user.let {
            binding.tvId.text = it?.id
            binding.tvMbti.text = it?.mbti
            binding.tvNickname.text = it?.nickname
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.tvLogout.setOnClickListener {
            userRepository.clearUserDetails()
            findNavController().navigate(R.id.action_mypage_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserData()
    }

    private fun setUserData() {
        userViewModel.userId.observe(viewLifecycleOwner) { id ->
            binding.tvId.text = id ?: "No ID"
        }

        userViewModel.userNickname.observe(viewLifecycleOwner) { nickname ->
            binding.tvNickname.text = nickname ?: "No Nickname"
        }

        userViewModel.userMbti.observe(viewLifecycleOwner) { mbti ->
            binding.tvMbti.text = mbti ?: "No MBTI"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

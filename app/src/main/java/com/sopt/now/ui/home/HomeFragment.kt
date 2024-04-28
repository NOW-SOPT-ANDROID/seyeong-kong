package com.sopt.now.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "FragmentHomeBinding is not initialized" }
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter()
        val profileAdapter = ProfileAdapter()

        binding.rvFriend.adapter = ConcatAdapter(profileAdapter, friendAdapter)

        viewModel.mockFriendList.observe(viewLifecycleOwner) { friendList ->
            friendAdapter.submitList(friendList)
        }

        viewModel.mockProfileList.observe(viewLifecycleOwner) { profileList ->
            profileAdapter.submitList(profileList)
        }
    }

    fun scrollToTop() {
        binding.rvFriend.smoothScrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
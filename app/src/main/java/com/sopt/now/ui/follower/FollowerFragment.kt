package com.sopt.now.ui.follower

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentFollowerBinding

class FollowerFragment : BaseFragment<FragmentFollowerBinding> (
    FragmentFollowerBinding::inflate
) {
    private val viewModel: FollowerViewModel by viewModels()
    private lateinit var followerAdapter: FollowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadFollowers()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvFollower.adapter = followerAdapter
    }

    private fun observeViewModel() {
        viewModel.followers.observe(viewLifecycleOwner) { followers ->
            followerAdapter.submitList(followers)
        }
    }
}
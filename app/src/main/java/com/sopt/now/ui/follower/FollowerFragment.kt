package com.sopt.now.ui.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding: FragmentFollowerBinding
        get() = requireNotNull(_binding) { "FragmentFollowerBinding is not initialized" }

    private val viewModel: FollowerViewModel by viewModels()
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
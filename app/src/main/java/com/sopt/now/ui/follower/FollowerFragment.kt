package com.sopt.now.ui.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentFollowerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding: FragmentFollowerBinding
        get() = requireNotNull(_binding) { "FragmentFollowerBinding is not initialized" }

    private val viewModel: FollowerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadFollowers()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val followerAdapter = FollowerAdapter(requireContext())
        binding.rvFollower.adapter = followerAdapter
    }

    private fun observeViewModel() {
        viewModel.followers.observe(viewLifecycleOwner) { followers ->
            (binding.rvFollower.adapter as FollowerAdapter).submitList(followers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvFollower?.adapter = null
        _binding = null
    }
}
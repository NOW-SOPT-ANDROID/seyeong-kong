package com.sopt.now.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { "FragmentSearchBinding is not initialized" }

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        setupObservers()
        searchViewModel.loadJson(requireContext())
    }

    private fun setupAdapters() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter
    }

    private fun setupObservers() {
        searchViewModel.repoList.observe(viewLifecycleOwner) { repoList ->
            repoAdapter.submitList(repoList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        binding.rvRepo.adapter = null
    }
}
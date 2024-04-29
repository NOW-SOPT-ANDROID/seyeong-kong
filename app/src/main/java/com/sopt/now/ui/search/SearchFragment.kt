package com.sopt.now.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.sopt.now.data.search.Repo
import com.sopt.now.databinding.FragmentSearchBinding
import java.io.IOException
import java.nio.charset.StandardCharsets

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { "FragmentSearchBinding is not initialized" }

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
        loadJson()
    }

    private fun setupAdapters() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter
    }

    private fun loadJson() {
        val fileName = "fake_repo_list.json"
        try {
            context?.assets?.open(fileName)?.use { inputStream ->
                val json = inputStream.readBytes().toString(StandardCharsets.UTF_8)
                parseJson(json)?.let { repoAdapter.submitList(it) }
            }
        } catch (e: IOException) {
            Log.e("SearchFragment", "Error loadJson", e)
        }
    }

    private fun parseJson(jsonString: String): List<Repo>? {
        return try {
            Gson().fromJson(jsonString, Array<Repo>::class.java).toList()
        } catch (e: Exception) {
            Log.e("SearchFragment", "Error parseJson", e)
            emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
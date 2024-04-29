package com.sopt.now.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.search.Repo
import com.sopt.now.databinding.ItemRepoBinding

class RepoViewHolder(private val binding: ItemRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(repoData: Repo) {
        binding.tvRepoName.text = repoData.name
        binding.tvLanguage.text = repoData.language
    }
}
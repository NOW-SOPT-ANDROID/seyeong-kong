package com.sopt.now.ui.follower

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.databinding.ItemFollowerBinding
import com.sopt.now.network.response.ResponseFollowerDto

class FollowerAdapter(private val context: Context) :
    ListAdapter<ResponseFollowerDto.Data, FollowerViewHolder>(
        FollowerDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class FollowerDiffCallback : DiffUtil.ItemCallback<ResponseFollowerDto.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseFollowerDto.Data,
            newItem: ResponseFollowerDto.Data,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseFollowerDto.Data,
            newItem: ResponseFollowerDto.Data,
        ): Boolean {
            return oldItem == newItem
        }
    }
}

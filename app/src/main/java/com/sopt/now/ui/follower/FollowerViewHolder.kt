package com.sopt.now.ui.follower

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.now.R
import com.sopt.now.databinding.ItemFollowerBinding
import com.sopt.now.network.response.ResponseFollowerDto

class FollowerViewHolder(private val binding: ItemFollowerBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(follower: ResponseFollowerDto.Data) {
        Glide.with(itemView.context).load(follower.avatar).into(binding.ivProfile)
        val fullName =
            context.getString(R.string.follower_name_format, follower.firstName, follower.lastName)
        binding.tvName.text = fullName
        binding.tvEmail.text = follower.email
    }
}
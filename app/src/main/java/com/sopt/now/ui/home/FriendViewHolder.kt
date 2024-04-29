package com.sopt.now.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend.Friend
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friend, onItemLongClick: ((Friend) -> Unit)?) {
        binding.run {
            ivFriendProfile.setImageResource(friendData.profileImg)
            tvName.text = friendData.name
            tvDescription.text = friendData.selfDescription
        }
        binding.root.setOnLongClickListener {
            onItemLongClick?.invoke(friendData)
            true
        }
    }
}
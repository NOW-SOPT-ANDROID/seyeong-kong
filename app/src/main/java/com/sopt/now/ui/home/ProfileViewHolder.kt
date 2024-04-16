package com.sopt.now.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemProfileBinding

class ProfileViewHolder(private val binding: ItemProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(profileData: Profile) {
        binding.run {
            ivProfile.setImageResource(profileData.profileImg)
            tvName.text = profileData.name
            tvDescription.text = profileData.selfDescription
        }
    }
}
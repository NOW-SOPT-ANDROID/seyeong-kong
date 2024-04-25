package com.sopt.now.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemProfileBinding

class ProfileAdapter() : RecyclerView.Adapter<ProfileViewHolder>() {
    private var profileList: List<Profile> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileBinding.inflate(inflater, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(profileList[position])
    }

    override fun getItemCount() = profileList.size

    fun setProfileList(profileList: List<Profile>) {
        this.profileList = profileList.toList()
        notifyDataSetChanged()
    }
}
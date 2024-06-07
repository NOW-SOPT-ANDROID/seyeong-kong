package com.sopt.now.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.sopt.now.R
import com.sopt.now.data.friend.Friend
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        initFab()
    }

    private fun setupAdapters() {
        val friendAdapter = FriendAdapter()
        friendAdapter.onItemLongClick = { friend ->
            showDeleteFriendDialog(friend)
        }

        val profileAdapter = ProfileAdapter()
        binding.rvFriend.adapter = ConcatAdapter(profileAdapter, friendAdapter)

        viewModel.friends.observe(viewLifecycleOwner) { friends ->
            friendAdapter.submitList(friends)
        }
        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            profileAdapter.submitList(profile)
        }
    }

    private fun initFab() {
        binding.fabAddFriend.setOnClickListener {
            showAddFriendDialog()
        }
    }

    private fun showAddFriendDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_friend, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.et_name)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.et_description)

        AlertDialog.Builder(requireContext())
            .setTitle("친구 추가")
            .setView(dialogView)
            .setPositiveButton("추가") { _, _ ->
                val name = nameEditText.text.toString()
                val description = descriptionEditText.text.toString()
                if (name.isNotEmpty() && description.isNotEmpty()) {
                    val newFriend = Friend(
                        profileImg = R.drawable.ic_mypage,
                        name = name,
                        selfDescription = description
                    )
                    viewModel.addFriend(newFriend)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun showDeleteFriendDialog(friend: Friend) {
        AlertDialog.Builder(requireContext())
            .setTitle("친구 삭제")
            .setMessage("${friend.name}을(를) 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                viewModel.deleteFriend(friend)
            }
            .setNegativeButton("취소", null)
            .show()
    }

    fun scrollToTop() {
        binding.rvFriend.smoothScrollToPosition(0)
    }
}
package com.sopt.now.ui.follower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.FragmentFollowerBinding
import com.sopt.now.network.response.ResponseFollowerDto
import com.sopt.now.network.service.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding: FragmentFollowerBinding
        get() = requireNotNull(_binding) { "FragmentFollowerBinding is not initialized" }

    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadUsers()
    }

    private fun setupRecyclerView() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvFollower.adapter = followerAdapter
    }

    private fun loadUsers() {
        ServicePool.followerService.getFollowers(2).enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { followers ->
                        followerAdapter.submitList(followers)
                    }
                } else {
                    Log.e(
                        "FollowerFragment",
                        "Fail load"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {
                Log.e("FollowerFragment", "fetching error")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
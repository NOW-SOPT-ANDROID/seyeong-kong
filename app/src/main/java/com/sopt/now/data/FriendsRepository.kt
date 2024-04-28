package com.sopt.now.data

import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    fun getAllFriends(): Flow<List<Friend>>
    suspend fun insertFriend(friend: Friend)
    suspend fun deleteFriend(friend: Friend)
}

class OfflineFriendsRepository(private val friendDao: FriendDao) : FriendsRepository {
    override fun getAllFriends(): Flow<List<Friend>> = friendDao.getAllFriends()
    override suspend fun insertFriend(friend: Friend) = friendDao.insertFriend(friend)
    override suspend fun deleteFriend(friend: Friend) = friendDao.deleteFriend(friend)
}
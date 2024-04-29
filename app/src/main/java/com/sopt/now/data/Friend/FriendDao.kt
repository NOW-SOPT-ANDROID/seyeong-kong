package com.sopt.now.data.Friend

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sopt.now.data.Friend.Friend
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {
    @Insert
    suspend fun insertFriend(friend: Friend)

    @Delete
    suspend fun deleteFriend(friend: Friend)

    @Query("SELECT * from friend_table ORDER BY id ASC")
    fun getAllFriends(): Flow<List<Friend>>
}

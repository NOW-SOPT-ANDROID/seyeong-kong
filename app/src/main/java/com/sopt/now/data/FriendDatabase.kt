package com.sopt.now.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        @Volatile
        private var Instance: FriendDatabase? = null

        fun getDatabase(context: Context): FriendDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FriendDatabase::class.java, "FriendDB")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
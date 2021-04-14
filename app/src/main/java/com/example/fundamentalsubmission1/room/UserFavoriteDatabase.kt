package com.example.fundamentalsubmission1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fundamentalsubmission1.models.UserFavorite
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(UserFavorite::class), version = 1, exportSchema = false)
public abstract class UserFavoriteDatabase : RoomDatabase() {
    abstract fun userFavoriteDao() : UserFavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: UserFavoriteDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): UserFavoriteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserFavoriteDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }

}
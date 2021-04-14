package com.example.fundamentalsubmission1.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fundamentalsubmission1.models.UserFavorite

@Dao
interface UserFavoriteDao {

    @Query("SELECT * FROM favorite_users")
    fun getAllFavoriteUsers(): kotlinx.coroutines.flow.Flow<List<UserFavorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userFavorite: UserFavorite)
}
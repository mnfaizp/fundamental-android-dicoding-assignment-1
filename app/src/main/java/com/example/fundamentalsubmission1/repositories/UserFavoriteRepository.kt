package com.example.fundamentalsubmission1.repositories

import androidx.annotation.WorkerThread
import com.example.fundamentalsubmission1.models.UserFavorite
import com.example.fundamentalsubmission1.room.UserFavoriteDao
import kotlinx.coroutines.flow.Flow

class UserFavoriteRepository(private val userFavoriteDao: UserFavoriteDao) {

    val allFavoriteUsers: Flow<List<UserFavorite>> = userFavoriteDao.getAllFavoriteUsers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userFavorite: UserFavorite) {
        userFavoriteDao.insert(userFavorite)
    }
}
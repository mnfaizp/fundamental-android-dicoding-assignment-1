package com.example.fundamentalsubmission1.repositories

import com.example.fundamentalsubmission1.api.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun getUser(user_id: String) = apiHelper.getUser(user_id)

    suspend fun getFollowers(username: String) = apiHelper.getFollowers(username)

    suspend fun getFollowing(username: String) = apiHelper.getFollowing(username)

    suspend fun getSearchUser(username: String) = apiHelper.getSearchUser(username)
}
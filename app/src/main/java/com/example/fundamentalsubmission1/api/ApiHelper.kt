package com.example.fundamentalsubmission1.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getAll()

    suspend fun getUser(user_id: String) = apiService.getUser(user_id)

    suspend fun getFollowers(username: String) = apiService.getFollowers(username)

    suspend fun getFollowing(username: String) = apiService.getFollowing(username)
}
package com.example.fundamentalsubmission1.api

import com.example.fundamentalsubmission1.models.User
import com.example.fundamentalsubmission1.models.UserDetail
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getAll(): ArrayList<User>

    @GET("users/{user_id}")
    @Headers("Authorization: token fa4d816fcb682aa23f8352c048efc230842a9386")
    suspend fun getUser(
        @Path("user_id") user_id: String
    ): UserDetail

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): ArrayList<User>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): ArrayList<User>
}
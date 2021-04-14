package com.example.fundamentalsubmission1.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
class UserFavorite(
        @PrimaryKey @ColumnInfo(name = "login") val login: String,
        @ColumnInfo(name = "avatar") val avatar_url: String,
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "url") val url: String
)

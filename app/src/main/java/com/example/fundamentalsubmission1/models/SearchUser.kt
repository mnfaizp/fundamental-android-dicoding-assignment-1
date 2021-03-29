package com.example.fundamentalsubmission1.models

data class SearchUser(
    val total_count: Int,
    val incomplete_results: Boolean,
    var items: List<User>
)

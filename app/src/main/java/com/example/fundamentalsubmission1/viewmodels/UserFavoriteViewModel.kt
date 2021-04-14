package com.example.fundamentalsubmission1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fundamentalsubmission1.models.UserFavorite
import com.example.fundamentalsubmission1.repositories.UserFavoriteRepository
import kotlinx.coroutines.launch

class UserFavoriteViewModel(private val repository: UserFavoriteRepository) : ViewModel() {

    val allFavoriteUsers: LiveData<List<UserFavorite>> = repository.allFavoriteUsers.asLiveData()

    fun insert(userFavorite: UserFavorite) = viewModelScope.launch {
        repository.insert(userFavorite)
    }
}
package com.example.fundamentalsubmission1.viewmodels

import androidx.lifecycle.*
import com.example.fundamentalsubmission1.repositories.UserRepository
import com.example.fundamentalsubmission1.utils.Resource
import kotlinx.coroutines.Dispatchers


class UserViewModel(private val userRepository: UserRepository) : ViewModel(){

    fun apiCallWithRepository() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = userRepository.getUsers()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Something Error"))
        }
    }
}
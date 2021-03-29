package com.example.fundamentalsubmission1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fundamentalsubmission1.repositories.UserRepository
import com.example.fundamentalsubmission1.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FollowingViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFollowing(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = userRepository.getFollowing(username)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }

}
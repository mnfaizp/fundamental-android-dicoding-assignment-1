package com.example.fundamentalsubmission1.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fundamentalsubmission1.repositories.UserRepository
import com.example.fundamentalsubmission1.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class DetailUserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUser(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = userRepository.getUser(username)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }

}
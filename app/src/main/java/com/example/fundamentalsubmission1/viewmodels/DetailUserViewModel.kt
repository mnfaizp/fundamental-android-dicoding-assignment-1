package com.example.fundamentalsubmission1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.fundamentalsubmission1.api.ApiHelper
import com.example.fundamentalsubmission1.api.RetrofitBuilder
import com.example.fundamentalsubmission1.models.UserDetail
import com.example.fundamentalsubmission1.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailUserViewModel(userRepository: UserRepository) : ViewModel() {

    private var detailUser: MutableLiveData<UserDetail> = MutableLiveData()

    fun getUserObserver() : MutableLiveData<UserDetail> {
        return detailUser
    }

    fun apiCallWithId(user_id: String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val instance = RetrofitBuilder.apiService
                val response = instance.getUser(user_id)

                detailUser.postValue(response)
            }


        } catch (e:Exception){
            e.printStackTrace()
        }


    }




}
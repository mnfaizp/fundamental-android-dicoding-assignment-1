package com.example.fundamentalsubmission1.utils

import com.example.fundamentalsubmission1.utils.Status.LOADING
import com.example.fundamentalsubmission1.utils.Status.SUCCESS
import com.example.fundamentalsubmission1.utils.Status.ERRORS

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERRORS, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}

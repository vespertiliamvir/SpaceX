package com.example.spacex.data.utils

sealed interface NetworkState<out T> {
    object Loading : NetworkState<Nothing>
    data class Success<T>(val data: T) : NetworkState<T>
    data class Failure<T>(val message: String) : NetworkState<T>
}
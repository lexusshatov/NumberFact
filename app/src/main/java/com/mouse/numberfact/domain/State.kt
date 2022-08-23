package com.mouse.numberfact.domain

sealed class State<out T : Any> {
    object Idle : State<Nothing>()
    object Loading : State<Nothing>()
    data class Success<T : Any>(val result: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
}
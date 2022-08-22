package com.mouse.numberfact.domain

sealed class State<out T : Any> {
    object Idle : State<Nothing>()
    object Loading : State<Nothing>()
    class Success<T : Any>(val result: T) : State<T>()
    class Error(val message: String) : State<Nothing>()
}
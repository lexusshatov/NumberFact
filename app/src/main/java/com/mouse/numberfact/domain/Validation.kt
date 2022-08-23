package com.mouse.numberfact.domain

interface Validation<T> {
    fun validate(value: T): Result

    data class Result(
        val errors: List<String> = emptyList(),
    ) {
        val success: Boolean = errors.isEmpty()
    }
}

class EmptyValidation <T>: Validation<T> {
    override fun validate(value: T) = Validation.Result()
}
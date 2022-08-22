package com.mouse.numberfact.domain

import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {

    @GET("{number}")
    suspend fun getNumberFact(
        @Path(value = "number") number: String,
    ): String

    @GET("/random/math")
    suspend fun getRandomNumber(): String
}
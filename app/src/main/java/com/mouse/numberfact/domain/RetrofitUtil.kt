package com.mouse.numberfact.domain

import com.mouse.numberfact.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {
    private const val OK_HTTP_CLIENT_CONNECT_TIMEOUT: Long = 10
    private const val OK_HTTP_CLIENT_READ_TIMEOUT: Long = 20

    private val logLevel: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        .connectTimeout(OK_HTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(OK_HTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun init(baseUrl: String = BuildConfig.API_BASE_URL): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }
}
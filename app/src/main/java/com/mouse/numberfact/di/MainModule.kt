package com.mouse.numberfact.di

import android.content.Context
import com.mouse.numberfact.data.database.NumberFactDao
import com.mouse.numberfact.data.database.NumberFactDatabaseUtil
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.RetrofitUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NumberFactDao {
        NumberFactDatabaseUtil.init(context)
        return NumberFactDatabaseUtil.dao
    }

    @Provides
    @Singleton
    fun provideApi(): NumberApi {
        return RetrofitUtil.init().create(NumberApi::class.java)
    }
}
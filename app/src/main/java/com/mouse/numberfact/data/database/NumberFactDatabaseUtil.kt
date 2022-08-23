package com.mouse.numberfact.data.database

import android.content.Context
import androidx.room.Room

object NumberFactDatabaseUtil {
    private const val DATABASE_NAME = "NumberFact.db"
    private lateinit var database: NumberFactDatabase
    val dao: NumberFactDao
        get() = database.numberFactDao()

    fun init(appContext: Context) {
        appContext.deleteDatabase(DATABASE_NAME)
        database = Room.databaseBuilder(
            appContext,
            NumberFactDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
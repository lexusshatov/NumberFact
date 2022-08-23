package com.mouse.numberfact.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberFactEntity::class], version = 2, exportSchema = false)
abstract class NumberFactDatabase: RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}
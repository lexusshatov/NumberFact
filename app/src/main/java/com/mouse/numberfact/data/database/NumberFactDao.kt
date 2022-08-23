package com.mouse.numberfact.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberFactDao {
    @Query("SELECT * FROM NumberFactEntity ORDER BY id DESC")
    fun getHistory(): LiveData<List<NumberFactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFact(numberFactEntity: NumberFactEntity)
}
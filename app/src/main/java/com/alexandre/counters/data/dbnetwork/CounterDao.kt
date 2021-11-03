package com.alexandre.counters.data.dbnetwork

import androidx.room.*
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO

@Dao
interface CounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(counter: CountersDTO)

    @Query("SELECT * FROM CounterTb")
    fun selectAll(): MutableList<CountersDTO>

    @Query("DELETE FROM CounterTb WHERE id = :id")
    fun deleteById(id : Int)

    @Update
    fun updateById(counter: CountersDTO)
}
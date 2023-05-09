package com.alexandre.counters.data.dbnetwork

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO

@Database(entities = [CountersDTO::class], version = 1)
abstract class CounterDatabse : RoomDatabase() {
    abstract fun counterDao(): CounterDao

}
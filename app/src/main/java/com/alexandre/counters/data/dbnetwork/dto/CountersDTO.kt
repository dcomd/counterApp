package com.alexandre.counters.data.dbnetwork.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "CounterTb")
class CountersDTO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int ,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "count") val count: Int)
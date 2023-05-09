package com.alexandre.counters.data.repository

import com.alexandre.counters.data.dbnetwork.dto.CountersDTO

interface CounterRepositoryImp {

    suspend fun syncBackEnd(list: List<CountersDTO>)

    suspend fun fetchList() :  MutableList<CountersDTO>

    fun create(counters: CountersDTO)

    fun delete(id: Int)

    fun update(counters: CountersDTO)
}
package com.alexandre.counters.domain.repository

import com.alexandre.counters.data.dbnetwork.CounterDatabse
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.example.counters.data.network.RetrofitInstanceCounters
import com.example.counters.data.network.response.CounterBody
import com.example.counters.data.network.response.SyncCountersBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CounterRespository(private var mDb: CounterDatabse) {

    private suspend fun syncBackEnd(list: List<CountersDTO>){
        if(list.isNotEmpty()){
            RetrofitInstanceCounters.getRetrofit().syncCounters(
                SyncCountersBody(
                    deletedCountersIds = list.map { it.id.toString() },
                    counters = list.map { CounterBody(it.id.toString(),it.title,it.count) }
                )
            )
        }
    }

    suspend fun fetchList() = withContext(Dispatchers.IO) {
        val getInformations = mDb?.counterDao()?.selectAll()
        getInformations?.let {
            syncBackEnd(it.toList())
        }
        return@withContext mDb?.counterDao()?.selectAll()
    }

     fun create(counters: CountersDTO){
         mDb?.counterDao()?.insert(counters)
    }

     fun delete(id: Int) {
         mDb?.counterDao()?.deleteById(id)
    }

     fun update(counters: CountersDTO) {
         mDb?.counterDao()?.updateById(counters)
    }

}
package com.alexandre.counters.data.repository

import com.alexandre.counters.data.dbnetwork.CounterDatabse
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.alexandre.counters.data.network.RetrofitInstanceCounters
import com.alexandre.counters.data.network.response.CounterBody
import com.alexandre.counters.data.network.response.SyncCountersBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CounterRepository(private var mDb: CounterDatabse) : CounterRepositoryImp {

    override suspend fun syncBackEnd(list: List<CountersDTO>){
        if(list.isNotEmpty()){
            RetrofitInstanceCounters.getRetrofit().syncCounters(
                SyncCountersBody(
                    deletedCountersIds = list.map { it.id.toString() },
                    counters = list.map { CounterBody(it.id.toString(),it.title,it.count) }
                )
            )
        }
    }

    override suspend fun fetchList() = withContext(Dispatchers.IO) {
        val getListProduct = mDb?.counterDao()?.selectAll()
        getListProduct?.let {
            syncBackEnd(it.toList())
        }
        return@withContext mDb?.counterDao()?.selectAll()
    }

    override fun create(counters: CountersDTO) = mDb?.counterDao()?.insert(counters)
    override fun delete(id: Int) = mDb?.counterDao()?.deleteById(id)
    override fun update(counters: CountersDTO)= mDb?.counterDao()?.updateById(counters)

}
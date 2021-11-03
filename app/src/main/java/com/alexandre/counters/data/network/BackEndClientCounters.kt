package com.example.counters.data.network

import com.example.counters.data.network.response.CounterBody
import com.example.counters.data.network.response.SyncCountersBody
import retrofit2.http.*

interface BackEndClientCounters {

    @GET("v1/counters")
    suspend fun getCounters(): List<CounterBody>

    @PUT("v1/counters/sync")
    suspend fun syncCounters(@Body sync: SyncCountersBody)
}
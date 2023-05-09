package com.alexandre.counters.data.network

import com.alexandre.counters.data.network.response.SyncCountersBody
import retrofit2.http.*

interface BackEndClientCounters {
    @PUT("v1/counters/sync")
    suspend fun syncCounters(@Body sync: SyncCountersBody)
}
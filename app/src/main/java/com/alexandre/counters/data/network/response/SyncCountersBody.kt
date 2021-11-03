package com.example.counters.data.network.response

import com.squareup.moshi.Json

data class SyncCountersBody(
    @Json(name = "deletedCounterIds") val deletedCountersIds: List<String>,
    @Json(name = "counters") val counters: List<CounterBody>
)

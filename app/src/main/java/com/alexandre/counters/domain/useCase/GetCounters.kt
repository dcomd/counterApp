package com.alexandre.counters.domain.useCase

import com.alexandre.counters.domain.model.Counters
import com.alexandre.counters.domain.repository.CounterRespository


class GetCounters(private val repository: CounterRespository) :
    UseCase<String, List<Counters>> {

    override suspend fun invoke(params: String): List<Counters> =
        repository.fetchList().map { Counters(it.id, it.title,it.count) }

}

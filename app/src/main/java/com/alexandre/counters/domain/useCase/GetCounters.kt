package com.alexandre.counters.domain.useCase

import com.alexandre.counters.domain.model.Counters
import com.alexandre.counters.data.repository.CounterRepository


class GetCounters(private val repository: CounterRepository) :
    UseCase<String, List<Counters>> {
    override suspend fun invoke(params: String): List<Counters> =
        repository.fetchList().map { Counters(it.id, it.title,it.count) }

}

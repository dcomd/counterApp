package com.alexandre.counters.domain.useCase

import com.alexandre.counters.domain.repository.CounterRespository


class DeleteCounters(private val repository: CounterRespository) :
    UseCase<Int, Unit> {

    override suspend fun invoke(params: Int) = repository.delete(params)
}

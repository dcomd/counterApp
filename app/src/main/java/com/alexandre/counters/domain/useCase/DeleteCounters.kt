package com.alexandre.counters.domain.useCase

import com.alexandre.counters.data.repository.CounterRepository

class DeleteCounters(private val repository: CounterRepository) :
    UseCase<Int, Unit> {
    override suspend fun invoke(params: Int) = repository.delete(params)
}

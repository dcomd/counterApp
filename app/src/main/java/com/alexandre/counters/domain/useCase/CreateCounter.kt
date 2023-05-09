package com.alexandre.counters.domain.useCase


import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.alexandre.counters.data.repository.CounterRepository

class CreateCounter(private val repository: CounterRepository) :
    UseCase<CountersDTO, Unit> {
    override suspend fun invoke(params: CountersDTO) =
        repository.create(params)
}

package com.alexandre.counters.domain.useCase

import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.alexandre.counters.domain.repository.CounterRespository

class Update(private val repository: CounterRespository) :
    UseCase<CountersDTO, Unit> {

    override suspend fun invoke(params: CountersDTO) = repository.update(params)
}


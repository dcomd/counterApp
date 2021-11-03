package com.alexandre.counters.domain.useCase


interface UseCase<ParamsType,ReturnType> {
    suspend operator fun invoke(params: ParamsType): ReturnType
}


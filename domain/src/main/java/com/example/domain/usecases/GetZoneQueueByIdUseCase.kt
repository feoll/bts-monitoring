package com.example.domain.usecases

import com.example.domain.entities.ZoneQueue
import com.example.domain.repository.ZoneRepository

class GetZoneQueueByIdUseCase(private val repository: ZoneRepository) {
    suspend operator fun invoke(id: String): Result<ZoneQueue> = repository.getZoneQueueById(id = id)
}
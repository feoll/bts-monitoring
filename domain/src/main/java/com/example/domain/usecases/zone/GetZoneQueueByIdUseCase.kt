package com.example.domain.usecases.zone

import com.example.domain.models.ZoneQueue
import com.example.domain.repository.ZoneRepository

class GetZoneQueueByIdUseCase(private val repository: ZoneRepository) {
    suspend operator fun invoke(id: String): Result<ZoneQueue> = repository.getZoneQueueById(id = id)
}
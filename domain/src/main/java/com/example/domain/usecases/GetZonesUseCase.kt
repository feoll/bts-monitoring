package com.example.domain.usecases

import com.example.domain.entities.Zone
import com.example.domain.repository.ZoneRepository

class GetZonesUseCase(private val repository: ZoneRepository) {
    suspend operator fun invoke(): Result<List<Zone>> = repository.getZones()
}
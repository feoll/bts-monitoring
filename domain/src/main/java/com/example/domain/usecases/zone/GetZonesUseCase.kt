package com.example.domain.usecases.zone

import com.example.domain.models.Zone
import com.example.domain.repository.ZoneRepository

class GetZonesUseCase(private val repository: ZoneRepository) {
    suspend operator fun invoke(): Result<List<Zone>> = repository.getZones()
}
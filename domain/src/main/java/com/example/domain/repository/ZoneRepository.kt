package com.example.domain.repository

import com.example.domain.entities.Zone
import com.example.domain.entities.ZoneQueue

interface ZoneRepository {
    suspend fun getZones(): Result<List<Zone>>
    suspend fun getZoneQueueById(id: String): Result<ZoneQueue>
}
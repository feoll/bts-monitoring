package com.example.domain.repository

import com.example.domain.models.Zone
import com.example.domain.models.ZoneQueue

interface ZoneRepository {
    suspend fun getZones(): Result<List<Zone>>
    suspend fun getZoneQueueById(id: String): Result<ZoneQueue>
}
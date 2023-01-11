package com.example.data.repository

import com.example.data.entities.ZoneQueueDto
import com.example.data.entities.ZoneResultDto
import com.example.data.mappers.toDomain
import com.example.data.source.ZoneDataSource
import com.example.domain.entities.Zone
import com.example.domain.entities.ZoneQueue
import com.example.domain.repository.ZoneRepository

class ZoneRepositoryImpl(private val remote: ZoneDataSource.Remote) : ZoneRepository {

    override suspend fun getZones(): Result<List<Zone>> =
        remote.getZones().map(ZoneResultDto::toDomain)

    override suspend fun getZoneQueueById(id: String): Result<ZoneQueue> =
        remote.getZoneQueueById(id = id).map(ZoneQueueDto::toDomain)
}
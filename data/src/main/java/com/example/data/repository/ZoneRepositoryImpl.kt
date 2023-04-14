package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.models.ZoneResultDto
import com.example.data.source.ZoneDataSource
import com.example.domain.models.Zone
import com.example.domain.models.ZoneQueue
import com.example.domain.repository.ZoneRepository

class ZoneRepositoryImpl(private val remote: ZoneDataSource.Remote) : ZoneRepository {

    override suspend fun getZones(): Result<List<Zone>> =
        remote.getZones().map(ZoneResultDto::toDomain)

    //    override suspend fun getZoneQueueById(id: String): Result<ZoneQueue> =
    //        remote.getZoneQueueById(id = id).map(ZoneQueueDto::toDomain)
    override suspend fun getZoneQueueById(id: String): Result<ZoneQueue> =
        remote.getZoneQueueById(id = id).fold(
            onSuccess = { Result.success(it.toDomain()) },
            onFailure = { Result.failure(exception = it) }
        )
}
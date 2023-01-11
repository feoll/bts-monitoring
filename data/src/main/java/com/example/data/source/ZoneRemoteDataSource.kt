package com.example.data.source

import com.example.data.api.ZoneApi
import com.example.data.entities.ZoneQueueDto
import com.example.data.entities.ZoneResultDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZoneRemoteDataSource(private val api: ZoneApi) : ZoneDataSource.Remote {
    override suspend fun getZones(): Result<ZoneResultDto> = withContext(Dispatchers.IO) {
        return@withContext Result.runCatching { api.getZones() }
    }

    override suspend fun getZoneQueueById(id: String): Result<ZoneQueueDto> = withContext(Dispatchers.IO) {
            return@withContext Result.runCatching { api.getZoneQueueById(id = id) }
    }
}
package com.example.data.source

import com.example.data.api.ZoneApi
import com.example.data.models.ZoneQueueDto
import com.example.data.models.ZoneResultDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZoneRemoteDataSourceImpl(private val api: ZoneApi) : ZoneDataSource.Remote {
    override suspend fun getZones(): Result<ZoneResultDto> = withContext(Dispatchers.IO) {
        return@withContext Result.runCatching { api.getZones() }
    }

    override suspend fun getZoneQueueById(id: String): Result<ZoneQueueDto> =
        withContext(Dispatchers.IO) {
            return@withContext Result.runCatching {
                val zone = api.getZoneQueueById(id = id)
                if (zone.info == null) throw NullPointerException()
                zone
            }
        }
}
package com.example.data.source

import com.example.data.entities.ZoneQueueDto
import com.example.data.entities.ZoneResultDto

interface ZoneDataSource {

    interface Remote {
        suspend fun getZones(): Result<ZoneResultDto>
        suspend fun getZoneQueueById(id: String): Result<ZoneQueueDto>
    }

}